package br.com.souza.hexagonal_arch.todoapi.application.ports.in.task;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;

public interface InsertTaskInputPort {

    void insert(Task task) throws Exception;
}
