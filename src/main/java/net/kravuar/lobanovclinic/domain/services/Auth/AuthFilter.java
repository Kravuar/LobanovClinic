package net.kravuar.lobanovclinic.domain.services.Auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authManager;
    private final JWTUtils jwtUtils;
    private final HandlerExceptionResolver exceptionResolver;

    public AuthFilter(AuthenticationManager authManager, JWTUtils jwtUtils, @Qualifier("handlerExceptionResolver") @NonNull HandlerExceptionResolver exceptionResolver) {
        setAuthenticationManager(authManager);
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            return authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException exception) {
            exceptionResolver.resolveException(request, response, null, exception);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        User user = (User) authResult.getPrincipal();

        response.addCookie(jwtUtils.getAccessCookie(user, request.getRequestURL().toString()));
        response.addCookie(jwtUtils.getRefreshToken(user, request.getRequestURL().toString()));
    }
}