package br.com.souza.hexagonal_arch.todoapi.config.usecases;

import br.com.souza.hexagonal_arch.todoapi.adapters.task.InsertTaskAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.FindUserByIdAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.FindZipCodeAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.InsertUserAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.SendEmailForConfirmationAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.UserAuthenticationAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.UserConfirmSecurityCodeAdapter;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.task.InsertTaskUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user.AuthenticateUserUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user.InsertUserUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user.UserConfirmSecurityCodeUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.InsertTaskInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.InsertUserInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserAuthenticationInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserConfirmSecurityCodeInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    InsertUserInputPort insertUserInputPort(InsertUserAdapter insertUserAdapter,
                                            FindZipCodeAdapter findZipCodeAdapter,
                                            SendEmailForConfirmationAdapter sendEmailForConfirmationAdapter) {
        return new InsertUserUseCase(insertUserAdapter, findZipCodeAdapter, sendEmailForConfirmationAdapter);
    }

    @Bean
    InsertTaskInputPort insertTaskInputPort(InsertTaskAdapter insertTaskAdapter,
                                            FindUserByIdAdapter findUserByIdAdapter) {
        return new InsertTaskUseCase(insertTaskAdapter, findUserByIdAdapter);
    }

    @Bean
    UserAuthenticationInputPort userAuthenticationInputPort(UserAuthenticationAdapter userAuthenticationAdapter) {
        return new AuthenticateUserUseCase(userAuthenticationAdapter);
    }

    @Bean
    UserConfirmSecurityCodeInputPort userConfirmSecurityCodeInputPort(UserConfirmSecurityCodeAdapter userConfirmSecurityCodeAdapter) {
        return new UserConfirmSecurityCodeUseCase(userConfirmSecurityCodeAdapter);
    }
}
