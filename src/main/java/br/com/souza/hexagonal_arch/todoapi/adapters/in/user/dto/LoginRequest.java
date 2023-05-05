package br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    private String email;
    private String password;

}
