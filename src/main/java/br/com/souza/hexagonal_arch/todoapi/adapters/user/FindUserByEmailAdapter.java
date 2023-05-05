package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.FindUserByEmailOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.EmailAlreadyRegisteredException;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class FindUserByEmailAdapter implements FindUserByEmailOutputPort {

    private final UserCollectionRepository userCollectionRepository;

    public FindUserByEmailAdapter(UserCollectionRepository userCollectionRepository) {
        this.userCollectionRepository = userCollectionRepository;
    }

    @Override
    public User find(User user) throws Exception {
        Optional<UserCollection> possibleUser = userCollectionRepository.findByEmail(user.getEmail());

        if(possibleUser.isPresent()){
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }

        return new User(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getValidEmail(),
                user.getStreet(),
                user.getLocality());
    }
}
