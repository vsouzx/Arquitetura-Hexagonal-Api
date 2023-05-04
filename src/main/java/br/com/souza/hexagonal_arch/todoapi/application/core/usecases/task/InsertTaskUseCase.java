package br.com.souza.hexagonal_arch.todoapi.application.core.usecases.task;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.InsertTaskInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.task.InsertTaskOutputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.FindUserByIdOutputPort;
import java.util.Optional;

public class InsertTaskUseCase implements InsertTaskInputPort {

    private final InsertTaskOutputPort insertTaskOutputPort;
    private final FindUserByIdOutputPort findUserByIdOutputPort;

    public InsertTaskUseCase(InsertTaskOutputPort insertTaskOutputPort,
                             FindUserByIdOutputPort findUserByIdOutputPort) {
        this.insertTaskOutputPort = insertTaskOutputPort;
        this.findUserByIdOutputPort = findUserByIdOutputPort;
    }

    @Override
    public void insert(Task task) throws Exception {
        //buscar user por Id para garantir que ele exista
        findUserByIdOutputPort.find(task.getUserId());

        insertTaskOutputPort.save(task);
    }
}
