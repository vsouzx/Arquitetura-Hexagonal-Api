package br.com.souza.hexagonal_arch.todoapi.application.ports.out;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;

public interface InsertUserOutputPort {

    void save(User user) throws Exception;
}
