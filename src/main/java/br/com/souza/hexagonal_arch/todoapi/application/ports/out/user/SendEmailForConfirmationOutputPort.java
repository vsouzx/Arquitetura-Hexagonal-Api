package br.com.souza.hexagonal_arch.todoapi.application.ports.out.user;

public interface SendEmailForConfirmationOutputPort {

    void send(String email) throws Exception;
}
