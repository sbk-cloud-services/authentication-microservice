package de.leuphana.shop.authenticationmicroservice.component.behaviour;

import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.component.structure.IncorrectAuthenticationTokenException;
import de.leuphana.shop.authenticationmicroservice.component.structure.IncorrectCredentialsException;

public interface AuthenticationService {
    public AuthenticationToken authenticate(String email, String password) throws IncorrectCredentialsException;
    public void verifyToken(String token) throws IncorrectAuthenticationTokenException;
}