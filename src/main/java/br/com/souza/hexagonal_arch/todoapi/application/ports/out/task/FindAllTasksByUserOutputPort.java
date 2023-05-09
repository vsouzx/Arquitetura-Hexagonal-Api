package br.com.souza.hexagonal_arch.todoapi.application.ports.out.task;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import java.util.List;

public interface FindAllTasksByUserOutputPort {

    List<Task> findAll(String userId);
}
