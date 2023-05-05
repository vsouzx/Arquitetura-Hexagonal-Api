package br.com.souza.hexagonal_arch.todoapi.config.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public JWTFilter(TokenProvider tokenProvider,
                     UserDetailsServiceImpl userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
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
            final String email = tokenProvider.getUsernameFromToken(jwt);

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
