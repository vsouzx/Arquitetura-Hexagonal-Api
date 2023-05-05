package br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.LoginRequest;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserAuthenticationInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserAuthenticationOutputPort;

public class AuthenticateUserUseCase implements UserAuthenticationInputPort {

    private final UserAuthenticationOutputPort userAuthenticationOutputPort;

    public AuthenticateUserUseCase(UserAuthenticationOutputPort userAuthenticationOutputPort) {
        this.userAuthenticationOutputPort = userAuthenticationOutputPort;
    }

    @Override
    public AuthenticationTokenResponse authenticate(LoginRequest loginRequest) throws Exception {
        return userAuthenticationOutputPort.authenticate(loginRequest);
    }

}
