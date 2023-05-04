package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class BadRequestException extends ErrorCodeException{

    public BadRequestException() {
        super(InternalTypeErrorCodesEnum.E400003);
    }

    public BadRequestException(String message) {
        super(InternalTypeErrorCodesEnum.E400003, message);
    }
}
