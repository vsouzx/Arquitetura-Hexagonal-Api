package br.com.souza.hexagonal_arch.todoapi.adapters.out.client;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.client.dto.ZipCodeResponse;
import br.com.souza.hexagonal_arch.todoapi.config.client.FeignClientConfiguration;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.BadRequestException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/", configuration = FeignClientConfiguration.class)
public interface ViaCepClient {

    @GetMapping(value = "/ws/{zipCode}/json")
    ZipCodeResponse findZipCode(@PathVariable("zipCode") String zipCode) throws BadRequestException;
}
