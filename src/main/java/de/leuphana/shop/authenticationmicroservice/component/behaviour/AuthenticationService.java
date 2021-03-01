package de.leuphana.shop.authenticationmicroservice.component.behaviour;

import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;

public interface AuthenticationService {
    public AuthenticationToken login(String email, String password);
    public void verifyToken(String token);
}