package de.leuphana.shop.authenticationmicroservice.component.structure;

public class IncorrectAuthenticationTokenException extends Exception {

    private static final long serialVersionUID = 1L;
    public IncorrectAuthenticationTokenException(String message) {
        super(message);
    }
}
