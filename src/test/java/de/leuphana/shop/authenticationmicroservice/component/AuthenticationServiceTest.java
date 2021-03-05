package de.leuphana.shop.authenticationmicroservice.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.leuphana.shop.authenticationmicroservice.component.behaviour.AuthenticationService;
import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.component.structure.IncorrectAuthenticationTokenException;
import de.leuphana.shop.authenticationmicroservice.component.structure.IncorrectCredentialsException;
@TestMethodOrder(OrderAnnotation.class)
public class AuthenticationServiceTest {
    private static AuthenticationService authenticationService;
    private static AuthenticationToken authenticationToken;

    @BeforeAll
    public static void setupBeforeClass() throws IncorrectCredentialsException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        authenticationService = (AuthenticationService) applicationContext.getBean("authenticationService");
        authenticationToken = authenticationService.authenticate("test", "");
    }

    @AfterAll
    public static void tearDownAfterClass() {

    }

    @Test
    @Order(1)
    public void canUserBeLoggedIn() throws IncorrectCredentialsException {
        authenticationToken = authenticationService.authenticate("test", "");
        Assertions.assertNotNull(authenticationToken);
    }

    @Test
    @Order(2)
    public void canTokenBeVerified() throws IncorrectAuthenticationTokenException {
        authenticationService.verifyToken(authenticationToken.getToken());

        Assertions.assertThrows(IncorrectAuthenticationTokenException.class, () -> {
            authenticationService.verifyToken("z.z.z");
        });
    }
}
