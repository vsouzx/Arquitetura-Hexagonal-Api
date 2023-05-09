package br.com.souza.hexagonal_arch.todoapi.application.core.usecases.task;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.FindAllTasksInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.task.FindAllTasksByUserOutputPort;
import java.util.List;

public class FindAllTasksUseCase implements FindAllTasksInputPort {

    private final FindAllTasksByUserOutputPort findAllTasksByUserOutputPort;

    public FindAllTasksUseCase(FindAllTasksByUserOutputPort findAllTasksByUserOutputPort) {
        this.findAllTasksByUserOutputPort = findAllTasksByUserOutputPort;
    }

    @Override
    public List<Task> findAll(String userId) throws Exception {
        return findAllTasksByUserOutputPort.findAll(userId);
    }
}
