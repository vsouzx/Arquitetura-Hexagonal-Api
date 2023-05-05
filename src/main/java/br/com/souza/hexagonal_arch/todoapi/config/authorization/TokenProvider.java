package br.com.souza.hexagonal_arch.todoapi.config.authorization;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.config.properties.SecurityProperties;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final int EXPIRATION_TIME = 86400000;
    private static final String ISSUER = "Web Token";
    private final SecurityProperties securityProperties;

    public AuthenticationTokenResponse generateToken(Authentication authentication) {
        String secret = securityProperties.getJwtKey();

        final Date now = new Date();
        Integer expirationInMillis = EXPIRATION_TIME;
        Date exp = new Date(System.currentTimeMillis() + expirationInMillis);

        final UserCollection user = getUsuario(authentication);

        final String auth = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                .compact();

        return AuthenticationTokenResponse.builder()
                .token_type("Bearer")
                .access_token(auth)
                .username(user.getEmail())
                .build();
    }

    public boolean isValid(String jwt, ServletResponse response) throws IOException {
        try {
            jwt = extractToken(jwt);
            String secret = securityProperties.getJwtKey();

            SignedJWT signedJWT = SignedJWT.parse(jwt);
            JWSVerifier verifier = new MACVerifier(securityProperties.getJwtKey().getBytes(StandardCharsets.UTF_8));
            if (!signedJWT.verify(verifier)) {
                log.error("Invalid JWT token!");
                ((HttpServletResponse) response).sendError(401);
                return false;
            }
            Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(jwt).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expirado: {}", e);
            ((HttpServletResponse) response).sendError(440);
            return false;
        }catch (Exception e) {
            log.error("Token inválido: {}", e);
            ((HttpServletResponse) response).sendError(401);
            return false;
        }
    }

    public String getUsernameFromToken(String jwt) {
        jwt = extractToken(jwt);
        String secret = securityProperties.getJwtKey();
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }

    public UserCollection getUsuario(Authentication authentication) {
        return (UserCollection) authentication.getPrincipal();
    }

    private String extractToken(String authToken) {
        if (authToken.toLowerCase().startsWith("bearer")) {
            return authToken.substring("bearer ".length());
        }
        return authToken;
    }
}
