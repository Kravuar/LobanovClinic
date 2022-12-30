package net.kravuar.lobanovclinic.domain.services.Auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.props.AppProps;
import net.kravuar.lobanovclinic.domain.services.exceptions.JWTNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class OnceAuthFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final AppProps props;

    @Qualifier("handlerExceptionResolver")
    @NonNull
    private final HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var cookie = WebUtils.getCookie(request, props.jwt.access_cookie_name);
            if (cookie == null)
                throw new JWTNotFoundException("Cookie с JWT токеном не найдено.");

            var decodedJWT = jwtUtils.decode(cookie.getValue());
            var authToken = new UsernamePasswordAuthenticationToken(
                    decodedJWT.getSubject(),
                    null,
                    Arrays.stream(decodedJWT.getClaim("rights")
                                    .asArray(String.class))
                            .map(SimpleGrantedAuthority::new).toList());

            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException exception) {
            exceptionResolver.resolveException(request, response, null, exception);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return props.security.unauthenticatedServlets.stream()
                .anyMatch(request.getServletPath()::startsWith);
    }
}
