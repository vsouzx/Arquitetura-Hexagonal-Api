package br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmationCodeRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String confirmationCode;

}
