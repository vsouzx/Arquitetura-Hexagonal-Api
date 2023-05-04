package br.com.souza.hexagonal_arch.todoapi.config.handler.enums;

public enum InternalTypeErrorCodesEnum {

    E500000("Internal error without mapped cause."),
    E400001("An user with this e-mail already exists: %s"),
    E400002("Invalid zipCode: %s"),
    E400003("Bad Request to: %s."),
    E400004("This user not exists: %s."),;

    private final String message;

    InternalTypeErrorCodesEnum( String message) {
        this.message = message;
    }

    public String getValue() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("Fault code: %s = %s.", getMessage());
    }

}
