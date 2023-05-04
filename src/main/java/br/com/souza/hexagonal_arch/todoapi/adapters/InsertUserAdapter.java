package br.com.souza.hexagonal_arch.todoapi.adapters;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.InsertUserOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.EmailAlreadyRegisteredException;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class InsertUserAdapter implements InsertUserOutputPort {

    private final UserCollectionRepository userCollectionRepository;

    public InsertUserAdapter(UserCollectionRepository userCollectionRepository) {
        this.userCollectionRepository = userCollectionRepository;
    }

    @Override
    public void save(User user) throws Exception {
        Optional<UserCollection> possibleUser = userCollectionRepository.findByEmail(user.getEmail());

        if(possibleUser.isPresent()){
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }

        userCollectionRepository.save(UserCollection.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .isValidEmail(false)
                        .street(user.getStreet())
                        .locality(user.getLocality())
                .build());
    }
}
