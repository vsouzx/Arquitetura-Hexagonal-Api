package br.com.souza.hexagonal_arch.todoapi.adapters.task;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.TaskCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.TaskCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.task.FindAllTasksByUserOutputPort;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FindAllTasksAdapter implements FindAllTasksByUserOutputPort {

    private final TaskCollectionRepository taskCollectionRepository;

    public FindAllTasksAdapter(TaskCollectionRepository taskCollectionRepository) {
        this.taskCollectionRepository = taskCollectionRepository;
    }

    @Override
    public List<Task> findAll(String userId) {
        List<TaskCollection> taskCollections = taskCollectionRepository.findAllById(userId);
        List<Task> response = new ArrayList<>();

        if(!taskCollections.isEmpty()){
            taskCollections.stream().forEach(t -> {
                response.add(new Task(t.getId(), t.getMessage(), t.getIsDone(), t.getUserId()));
            });
        }
        return response;
    }
}
