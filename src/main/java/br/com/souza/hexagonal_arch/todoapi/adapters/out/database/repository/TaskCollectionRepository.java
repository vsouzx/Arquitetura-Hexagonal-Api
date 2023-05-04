package br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.TaskCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskCollectionRepository extends MongoRepository<TaskCollection, String> {

    @Query(value = "{'userId': ?0 }")
    Optional<TaskCollection> findByUserId(String userId);
}
