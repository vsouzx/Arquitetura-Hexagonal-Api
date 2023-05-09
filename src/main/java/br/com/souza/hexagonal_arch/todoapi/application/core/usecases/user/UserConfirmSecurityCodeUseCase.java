package br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user;

import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserConfirmSecurityCodeInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserConfirmSecurityCodeOutputPort;

public class UserConfirmSecurityCodeUseCase implements UserConfirmSecurityCodeInputPort {

    private final UserConfirmSecurityCodeOutputPort userConfirmSecurityCodeOutputPort;

    public UserConfirmSecurityCodeUseCase(UserConfirmSecurityCodeOutputPort userConfirmSecurityCodeOutputPort) {
        this.userConfirmSecurityCodeOutputPort = userConfirmSecurityCodeOutputPort;
    }

    @Override
    public void confirm(String confirmationCode, String email) throws Exception {
        userConfirmSecurityCodeOutputPort.confirm(confirmationCode, email);
    }

}
