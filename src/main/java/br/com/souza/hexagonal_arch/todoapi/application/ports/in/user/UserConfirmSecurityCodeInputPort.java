package br.com.souza.hexagonal_arch.todoapi.application.ports.in.user;

public interface UserConfirmSecurityCodeInputPort {

    void confirm(String confirmationCode, String email) throws Exception;
}
