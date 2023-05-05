package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.LoginRequest;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserAuthenticationOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.authorization.TokenProvider;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.BadCredencialsException;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.NotConfirmedEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAuthenticationAdapter implements UserAuthenticationOutputPort {

    private final UserCollectionRepository userCollectionRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public UserAuthenticationAdapter(UserCollectionRepository userCollectionRepository,
                                     AuthenticationManager authenticationManager,
                                     TokenProvider tokenProvider) {
        this.userCollectionRepository = userCollectionRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthenticationTokenResponse authenticate(final LoginRequest loginRequest) throws Exception {

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword(), null);
        UserCollection user;

        try{
            authentication = authenticationManager.authenticate(authentication);
            user = tokenProvider.getUsuario(authentication);
        }catch (Exception e){
            log.error("Bad credentials");
            throw new BadCredencialsException();
        }

        /*if(!user.getIsValidEmail()){
            throw new NotConfirmedEmailException();
        }*/

        return tokenProvider.generateToken(authentication);
    }
}
