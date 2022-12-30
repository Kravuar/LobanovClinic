package net.kravuar.lobanovclinic.domain.services.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTNotFoundException extends JWTVerificationException {
    public JWTNotFoundException(String message) {
        super(message);
    }
}
