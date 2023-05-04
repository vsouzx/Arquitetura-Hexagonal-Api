package br.com.souza.hexagonal_arch.todoapi.application.ports.in.user;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;

public interface InsertUserInputPort {

    void insertUser(User user, String zipCode) throws Exception;
}
