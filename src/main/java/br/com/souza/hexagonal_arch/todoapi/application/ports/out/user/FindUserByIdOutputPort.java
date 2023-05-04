package br.com.souza.hexagonal_arch.todoapi.application.ports.out.user;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;

public interface FindUserByIdOutputPort {

    User find(String id) throws Exception;
}
