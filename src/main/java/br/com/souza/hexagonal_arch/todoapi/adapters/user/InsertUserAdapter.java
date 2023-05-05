package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.InsertUserOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InsertUserAdapter implements InsertUserOutputPort {

    private final UserCollectionRepository userCollectionRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public InsertUserAdapter(UserCollectionRepository userCollectionRepository,
                             PasswordEncoder bCryptPasswordEncoder) {
        this.userCollectionRepository = userCollectionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) throws Exception {
        userCollectionRepository.save(UserCollection.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .isValidEmail(false)
                .street(user.getStreet())
                .locality(user.getLocality())
                .build());
    }
}
