package br.com.souza.hexagonal_arch.todoapi.config.authorization;

import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.FindUserByIdOutputPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final FindUserByIdOutputPort findUserByIdOutputPort;
    private final TokenProvider tokenProvider;

    public JWTFilter(FindUserByIdOutputPort findUserByIdOutputPort, TokenProvider tokenProvider) {
        this.findUserByIdOutputPort = findUserByIdOutputPort;
        this.tokenProvider = tokenProvider;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = httpServletRequest.getHeader(AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (StringUtils.hasText(jwt) && this.tokenProvider.isValid(jwt, servletResponse)) {
            final String id = tokenProvider.getUsernameFromToken(jwt);

            User user = findUserByIdOutputPort.find(id);

            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getEmail();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
