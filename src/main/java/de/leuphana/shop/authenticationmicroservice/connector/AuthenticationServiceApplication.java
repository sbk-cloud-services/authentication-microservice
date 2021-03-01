package de.leuphana.shop.authenticationmicroservice.connector;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class AuthenticationServiceApplication implements CommandLineRunner {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

    @Override
    public void run(String... args) throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
    }
}
