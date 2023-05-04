package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class EmailAlreadyRegisteredException extends ErrorCodeException{

    public EmailAlreadyRegisteredException() {
        super(InternalTypeErrorCodesEnum.E400001);
    }

    public EmailAlreadyRegisteredException(String message) {
        super(InternalTypeErrorCodesEnum.E400001, message);
    }
}
