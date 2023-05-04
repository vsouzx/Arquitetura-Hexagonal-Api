package br.com.souza.hexagonal_arch.todoapi.application.core.usecases;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.core.dtos.ZipCode;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.InsertUserInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.FindZipCodeOutputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.InsertUserOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.BadRequestException;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.InvalidZipCodeException;

public class InsertUserUseCase implements InsertUserInputPort {

    private final InsertUserOutputPort insertUserOutputPort;
    private final FindZipCodeOutputPort findZipCodeOutputPort;

    public InsertUserUseCase(InsertUserOutputPort insertUserOutputPort,
                             FindZipCodeOutputPort findZipCodeOutputPort) {
        this.insertUserOutputPort = insertUserOutputPort;
        this.findZipCodeOutputPort = findZipCodeOutputPort;
    }

    @Override
    public void insertUser(User user, String zipCode) throws Exception{

        ZipCode zipCodeResponse;
        //buscar zipCode, se for inválido, retornar exceção
        try {
            zipCodeResponse = findZipCodeOutputPort.find(zipCode);
        }catch (BadRequestException e){
            throw new InvalidZipCodeException(zipCode);
        }

        user.setStreet(zipCodeResponse.getStreet());
        user.setLocality(zipCodeResponse.getLocality());

        insertUserOutputPort.save(user);
    }
}
