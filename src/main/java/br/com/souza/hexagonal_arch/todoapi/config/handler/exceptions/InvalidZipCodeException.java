package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class InvalidZipCodeException extends ErrorCodeException{

    public InvalidZipCodeException() {
        super(InternalTypeErrorCodesEnum.E400002);
    }

    public InvalidZipCodeException(String message) {
        super(InternalTypeErrorCodesEnum.E400002, message);
    }
}
