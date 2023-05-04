package br.com.souza.hexagonal_arch.todoapi.config.usecases;

import br.com.souza.hexagonal_arch.todoapi.adapters.task.InsertTaskAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.FindUserByIdAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.FindZipCodeAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.user.InsertUserAdapter;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.task.InsertTaskUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.user.InsertUserUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.InsertTaskInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.InsertUserInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    InsertUserInputPort insertUserInputPort(InsertUserAdapter insertUserAdapter,
                                            FindZipCodeAdapter findZipCodeAdapter){
        return new InsertUserUseCase(insertUserAdapter, findZipCodeAdapter);
    }

    @Bean
    InsertTaskInputPort insertTaskInputPort(InsertTaskAdapter insertTaskAdapter,
                                            FindUserByIdAdapter findUserByIdAdapter){
        return new InsertTaskUseCase(insertTaskAdapter, findUserByIdAdapter);
    }
}
