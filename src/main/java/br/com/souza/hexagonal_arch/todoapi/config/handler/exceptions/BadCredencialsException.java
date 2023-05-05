package br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions;

import br.com.souza.hexagonal_arch.todoapi.config.handler.enums.InternalTypeErrorCodesEnum;

public class BadCredencialsException extends ErrorCodeException{

    public BadCredencialsException() {
        super(InternalTypeErrorCodesEnum.E400005);
    }

    public BadCredencialsException(String message) {
        super(InternalTypeErrorCodesEnum.E400005, message);
    }
}
