package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class EmailAlreadyConfirmedException extends ErrorCodeException{

    public EmailAlreadyConfirmedException() {
        super(InternalTypeErrorCodesEnum.E400008);
    }

    public EmailAlreadyConfirmedException(String message) {
        super(InternalTypeErrorCodesEnum.E400008, message);
    }
}
