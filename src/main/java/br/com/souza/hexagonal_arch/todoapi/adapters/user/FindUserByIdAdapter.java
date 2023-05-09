package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.FindUserByIdOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.UserNotExistsException;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class FindUserByIdAdapter implements FindUserByIdOutputPort {

    private final UserCollectionRepository userCollectionRepository;

    public FindUserByIdAdapter(UserCollectionRepository userCollectionRepository) {
        this.userCollectionRepository = userCollectionRepository;
    }

    @Override
    public User find(String userId) throws Exception {
        Optional<UserCollection> possibleUser = userCollectionRepository.findById(userId);

        if (possibleUser.isEmpty()) {
            throw new UserNotExistsException(userId);
        }

        UserCollection user = possibleUser.get();

        return new User(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getIsValidEmail(),
                user.getStreet(),
                user.getLocality());
    }
}
