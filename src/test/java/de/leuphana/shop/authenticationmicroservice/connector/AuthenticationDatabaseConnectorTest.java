package de.leuphana.shop.authenticationmicroservice.connector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.leuphana.shop.authenticationmicroservice.component.behaviour.exception.IncorrectCredentialsException;


public class AuthenticationDatabaseConnectorTest {

    private static AuthenticationDatabaseConnector authenticationDatabaseConnector;
    
    @BeforeAll
    public static void setupBeforeClass() throws IncorrectCredentialsException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        authenticationDatabaseConnector = (AuthenticationDatabaseConnector) applicationContext.getBean("authenticationDatabaseConnector");
    }

    @Test
    public void canUserBeFetched() {
        authenticationDatabaseConnector.getUser("test");
    }
}
