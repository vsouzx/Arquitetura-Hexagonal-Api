package br.com.souza.hexagonal_arch.todoapi.application.ports.out.user;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;

public interface FindUserByEmailOutputPort {

    User find(User user) throws Exception;
}
