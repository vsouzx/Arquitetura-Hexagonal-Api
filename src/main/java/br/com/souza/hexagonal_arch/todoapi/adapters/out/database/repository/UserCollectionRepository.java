package br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserCollectionRepository extends MongoRepository<UserCollection, String> {

    @Query(value = "{'email': ?0 }")
    Optional<UserCollection> findByEmail(String email);
}
