package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class InvalidConfirmationCodeException extends ErrorCodeException{

    public InvalidConfirmationCodeException() {
        super(InternalTypeErrorCodesEnum.E400007);
    }

    public InvalidConfirmationCodeException(String message) {
        super(InternalTypeErrorCodesEnum.E400007, message);
    }
}
