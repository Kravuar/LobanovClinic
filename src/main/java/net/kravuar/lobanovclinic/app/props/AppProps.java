package net.kravuar.lobanovclinic.app.props;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("app")
@RequiredArgsConstructor
public class AppProps {
    public final JWT jwt;
    public final Security security;

    @RequiredArgsConstructor
    public static class JWT {
        public final long access_token_expiration;
        public final long refresh_token_expiration;
        public final String access_cookie_name;
        public final String refresh_cookie_name;
    }
    @RequiredArgsConstructor
    public static class Security {
        public final List<String> unauthenticatedServlets;
    }
}
