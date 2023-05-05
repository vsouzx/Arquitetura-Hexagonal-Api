package br.com.souza.hexagonal_arch.todoapi.config.authorization;

import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.FindUserByIdOutputPort;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final FindUserByIdOutputPort findUserByIdOutputPort;

    public JWTConfigurer(TokenProvider tokenProvider, FindUserByIdOutputPort findUserByIdOutputPort) {
        this.tokenProvider = tokenProvider;
        this.findUserByIdOutputPort = findUserByIdOutputPort;
    }

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(findUserByIdOutputPort, tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
