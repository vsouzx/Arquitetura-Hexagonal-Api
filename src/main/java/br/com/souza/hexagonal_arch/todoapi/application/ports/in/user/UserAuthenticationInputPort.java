package br.com.souza.hexagonal_arch.todoapi.application.ports.in.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.LoginRequest;

public interface UserAuthenticationInputPort {

    AuthenticationTokenResponse authenticate(LoginRequest loginRequest) throws Exception;
}
