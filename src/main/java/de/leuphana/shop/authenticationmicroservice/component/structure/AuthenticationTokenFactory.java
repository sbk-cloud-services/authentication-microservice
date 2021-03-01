package de.leuphana.shop.authenticationmicroservice.component.structure;

public class AuthenticationTokenFactory {
    public AuthenticationToken create() {
        AuthenticationToken authenticationToken = new AuthenticationToken();
        String token = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";

        for(Integer i = 0; i < 128; i++) {
            Integer index = (int) Math.round(Math.random() * (alphabet.length() -  1));
            token += alphabet.charAt(index);
        }

        authenticationToken.setToken(token);
        
        return authenticationToken;
    }
}
