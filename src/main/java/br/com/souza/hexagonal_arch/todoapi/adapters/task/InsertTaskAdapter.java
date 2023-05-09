package br.com.souza.hexagonal_arch.todoapi.adapters.task;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.TaskCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.TaskCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.task.InsertTaskOutputPort;
import org.springframework.stereotype.Component;

@Component
public class InsertTaskAdapter implements InsertTaskOutputPort {

    private final TaskCollectionRepository taskCollectionRepository;

    public InsertTaskAdapter(TaskCollectionRepository taskCollectionRepository) {
        this.taskCollectionRepository = taskCollectionRepository;
    }

    @Override
    public void save(String message, String userId) {
        taskCollectionRepository.save(TaskCollection.builder()
                .message(message)
                .isDone(false)
                .userId(userId)
                .build());
    }

}
