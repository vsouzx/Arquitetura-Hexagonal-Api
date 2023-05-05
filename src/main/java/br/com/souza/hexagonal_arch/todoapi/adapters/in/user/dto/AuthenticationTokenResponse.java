package br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationTokenResponse {

    private String token_type;
    private String access_token;
    private String username;

}
