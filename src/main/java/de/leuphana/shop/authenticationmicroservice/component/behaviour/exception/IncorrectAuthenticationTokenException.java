package de.leuphana.shop.authenticationmicroservice.component.behaviour.exception;

public class IncorrectAuthenticationTokenException extends Exception {

    private static final long serialVersionUID = 1L;
    public IncorrectAuthenticationTokenException(String message) {
        super(message);
    }
}
