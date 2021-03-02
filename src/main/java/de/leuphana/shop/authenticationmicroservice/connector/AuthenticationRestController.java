package de.leuphana.shop.authenticationmicroservice.connector;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.authenticationmicroservice.component.behaviour.AuthenticationService;
import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;

@RestController
public class AuthenticationRestController {

    @PostMapping("/authenticate")
    @ResponseBody
    public AuthenticationToken authenticate(@RequestBody EmailPasswordCredentials emailPasswordCredentials) {
        String email = emailPasswordCredentials.getEmail();
        String password = emailPasswordCredentials.getPassword();

        AuthenticationService authenticationService = (AuthenticationService) AuthenticationServiceApplication.getApplicationContext().getBean("authenticationService");

        try {
            AuthenticationToken authenticationToken = authenticationService.authenticate(email, password);
            return authenticationToken;
        } catch (Exception exception) {
            return null;
        }
    }
}
