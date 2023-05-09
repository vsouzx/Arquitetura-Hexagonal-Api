package br.com.souza.hexagonal_arch.todoapi.config.authorization;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConfig implements UserDetailsService {

    private final UserCollectionRepository userCollectionRepository;

    public UserDetailsConfig(UserCollectionRepository userCollectionRepository) {
        this.userCollectionRepository = userCollectionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userCollectionRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: "+ email));
    }

}
