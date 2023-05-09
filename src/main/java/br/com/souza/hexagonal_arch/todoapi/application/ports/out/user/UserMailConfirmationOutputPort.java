package br.com.souza.hexagonal_arch.todoapi.application.ports.out.user;

public interface UserMailConfirmationOutputPort {

    void sendMailConfirmation(String userName, String email, String confirmationCode) throws Exception;
}
