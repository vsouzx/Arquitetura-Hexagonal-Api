package br.com.souza.hexagonal_arch.todoapi.application.ports.in.task;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import java.util.List;

public interface FindAllTasksInputPort {

    List<Task> findAll(String userId) throws Exception;
}
