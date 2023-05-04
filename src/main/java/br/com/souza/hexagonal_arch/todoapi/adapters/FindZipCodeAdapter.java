package br.com.souza.hexagonal_arch.todoapi.adapters;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.client.ViaCepClient;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.client.dto.ZipCodeResponse;
import br.com.souza.hexagonal_arch.todoapi.application.core.dtos.ZipCode;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.FindZipCodeOutputPort;
import org.springframework.stereotype.Component;

@Component
public class FindZipCodeAdapter implements FindZipCodeOutputPort {

    private final ViaCepClient viaCepClient;

    public FindZipCodeAdapter(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @Override
    public ZipCode find(String zipCode) throws Exception{
        ZipCodeResponse response = viaCepClient.findZipCode(zipCode);
        return new ZipCode(response.getLogradouro(), response.getLocalidade());
    }
}
