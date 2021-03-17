package de.leuphana.shop.authenticationmicroservice.component.behaviour;

import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.component.behaviour.exception.IncorrectAuthenticationTokenException;
import de.leuphana.shop.authenticationmicroservice.component.behaviour.exception.IncorrectCredentialsException;

public interface AuthenticationService {
    public AuthenticationToken authenticate(String email, String password) throws IncorrectCredentialsException;
    public void verifyToken(String token) throws IncorrectAuthenticationTokenException;
}