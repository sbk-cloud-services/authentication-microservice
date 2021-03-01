package de.leuphana.shop.authenticationmicroservice.connector.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "authenticationToken")
public class AuthenticationTokenEntity {
    @Id
    private String token;
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
        
}
