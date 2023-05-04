package br.com.souza.hexagonal_arch.todoapi.application.ports.out;

import br.com.souza.hexagonal_arch.todoapi.application.core.dtos.ZipCode;

public interface FindZipCodeOutputPort {

    ZipCode find(String zipCode)  throws Exception;
}
