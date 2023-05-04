package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class UserNotExistsException extends ErrorCodeException{

    public UserNotExistsException() {
        super(InternalTypeErrorCodesEnum.E400004);
    }

    public UserNotExistsException(String message) {
        super(InternalTypeErrorCodesEnum.E400004, message);
    }
}
