package br.com.souza.hexagonal_arch.todoapi.config.usecases;

import br.com.souza.hexagonal_arch.todoapi.adapters.FindZipCodeAdapter;
import br.com.souza.hexagonal_arch.todoapi.adapters.InsertUserAdapter;
import br.com.souza.hexagonal_arch.todoapi.application.core.usecases.InsertUserUseCase;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.InsertUserInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    InsertUserInputPort insertUserInputPort(InsertUserAdapter insertUserAdapter,
                                            FindZipCodeAdapter findZipCodeAdapter){
        return new InsertUserUseCase(insertUserAdapter, findZipCodeAdapter);
    }
}
