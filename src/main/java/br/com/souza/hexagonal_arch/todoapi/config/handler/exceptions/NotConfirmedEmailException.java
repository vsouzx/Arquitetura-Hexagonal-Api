package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class NotConfirmedEmailException extends ErrorCodeException{

    public NotConfirmedEmailException() {
        super(InternalTypeErrorCodesEnum.E400006);
    }

    public NotConfirmedEmailException(String message) {
        super(InternalTypeErrorCodesEnum.E400006, message);
    }
}
