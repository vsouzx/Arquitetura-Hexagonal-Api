package br.com.souza.hexagonal_arch.todoapi.adapters.out.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZipCodeResponse {

    private String logradouro;
    private String localidade;
}
