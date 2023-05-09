package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserConfirmSecurityCodeOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.EmailAlreadyConfirmedException;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.InvalidConfirmationCodeException;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.UserNotExistsException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserConfirmSecurityCodeAdapter implements UserConfirmSecurityCodeOutputPort {

    private final UserCollectionRepository userCollectionRepository;

    public UserConfirmSecurityCodeAdapter(UserCollectionRepository userCollectionRepository) {
        this.userCollectionRepository = userCollectionRepository;
    }

    @Override
    public void confirm(String confirmationCode, String email) throws Exception {
        Optional<UserCollection> user = userCollectionRepository.findByEmail(email);

        if(user.isEmpty()) {
            throw new UserNotExistsException(email);
        }else if(user.get().getIsValidEmail()) {
            throw new EmailAlreadyConfirmedException();
        }else if(user.get().getConfirmationCode().equals(confirmationCode)) {
            user.get().setIsValidEmail(true);
            userCollectionRepository.save(user.get());
        } else {
            throw new InvalidConfirmationCodeException();
        }
    }
}
