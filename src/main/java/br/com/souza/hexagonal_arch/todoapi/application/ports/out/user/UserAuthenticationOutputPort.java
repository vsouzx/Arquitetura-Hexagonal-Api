package br.com.souza.hexagonal_arch.todoapi.application.ports.out.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.LoginRequest;

public interface UserAuthenticationOutputPort {

    AuthenticationTokenResponse authenticate(LoginRequest loginRequest) throws Exception;
}
