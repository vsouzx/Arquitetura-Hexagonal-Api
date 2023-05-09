package br.com.souza.hexagonal_arch.todoapi.application.ports.out.task;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;

public interface InsertTaskOutputPort {

    void save(String message, String userId);
}
