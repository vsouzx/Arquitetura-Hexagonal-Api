package br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.ZipCode;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.InsertUserInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.FindZipCodeOutputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.InsertUserOutputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.SendEmailForConfirmationOutputPort;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.BadRequestException;
import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.InvalidZipCodeException;

public class InsertUserUseCase implements InsertUserInputPort {

    private final InsertUserOutputPort insertUserOutputPort;
    private final FindZipCodeOutputPort findZipCodeOutputPort;
    private final SendEmailForConfirmationOutputPort sendEmailForConfirmationOutputPort;

    public InsertUserUseCase(InsertUserOutputPort insertUserOutputPort,
                             FindZipCodeOutputPort findZipCodeOutputPort,
                             SendEmailForConfirmationOutputPort sendEmailForConfirmationOutputPort) {
        this.insertUserOutputPort = insertUserOutputPort;
        this.findZipCodeOutputPort = findZipCodeOutputPort;
        this.sendEmailForConfirmationOutputPort = sendEmailForConfirmationOutputPort;
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

        //Sending message to kafka topic
        sendEmailForConfirmationOutputPort.send(user.getEmail());
    }
}
