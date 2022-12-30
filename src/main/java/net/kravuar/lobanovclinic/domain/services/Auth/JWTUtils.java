package net.kravuar.lobanovclinic.domain.services.Auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import net.kravuar.lobanovclinic.app.props.AppProps;
import net.kravuar.lobanovclinic.domain.services.exceptions.JWTNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JWTUtils {
    private final Algorithm algorithm;
    private final AppProps props;

    public Cookie getAccessCookie(User user, String issuer) {
        var token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date().toInstant().plusSeconds(props.jwt.access_token_expiration))
                .withIssuer(issuer)
                .withClaim("rights", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(algorithm);
        var cookie = new Cookie(props.jwt.access_cookie_name, "Bearer_" + token);
        cookie.setMaxAge((int) props.jwt.access_token_expiration);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
    public Cookie getRefreshToken(User user, String issuer) {
        var token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date().toInstant().plusSeconds(props.jwt.refresh_token_expiration))
                .withIssuer(issuer)
                .sign(algorithm);
        var cookie = new Cookie(props.jwt.refresh_cookie_name, "Bearer_" + token);
        cookie.setMaxAge((int) props.jwt.refresh_token_expiration);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
    public List<Cookie> getDeleteCookies() {
        var cookies = new ArrayList<Cookie>(2);
        for (var cookieName: List.of(props.jwt.access_cookie_name, props.jwt.refresh_cookie_name)) {
            var cookie = new Cookie(cookieName, "deleted");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            cookie.setPath("/");

            cookies.add(cookie);
        }

        return cookies;
    }
    public DecodedJWT decode(String token) {
        if (token != null && token.startsWith("Bearer_")) {
            String trimmed = token.substring("Bearer_".length());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(trimmed);
        } else throw new JWTNotFoundException("Токен не найден.");
    }
}
