package de.leuphana.shop.authenticationmicroservice.component.behaviour.exception;

public class IncorrectCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;
    
    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
