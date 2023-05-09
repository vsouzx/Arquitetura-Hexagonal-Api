package br.com.souza.hexagonal_arch.todoapi.application.ports.out.user;

public interface UserConfirmSecurityCodeOutputPort {

    void confirm(String confirmationCode, String email) throws Exception;
}
