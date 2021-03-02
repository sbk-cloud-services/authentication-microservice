package de.leuphana.shop.authenticationmicroservice.component.structure;

public class IncorrectCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;
    
    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
