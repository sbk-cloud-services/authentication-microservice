package de.leuphana.shop.authenticationmicroservice.component.behaviour;

import org.springframework.security.crypto.password.PasswordEncoder;

import de.leuphana.shop.authenticationmicroservice.component.structure.IncorrectCredentialsException;
import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationTokenFactory;
import de.leuphana.shop.authenticationmicroservice.component.structure.IncorrectAuthenticationTokenException;
import de.leuphana.shop.authenticationmicroservice.component.structure.User;
import de.leuphana.shop.authenticationmicroservice.connector.AuthenticationDatabaseConnector;

public class AuthenticationServiceImplementation implements AuthenticationService {

	private AuthenticationDatabaseConnector authenticationDatabaseConnector;
	private PasswordEncoder passwordEncoder;

	public AuthenticationServiceImplementation(AuthenticationDatabaseConnector authenticationDatabaseConnector, PasswordEncoder passwordEncoder) {
		this.authenticationDatabaseConnector = authenticationDatabaseConnector;
		this.passwordEncoder = passwordEncoder;
	}

	public AuthenticationToken login(String email, String password) {
		User user = authenticationDatabaseConnector.getUser(email);

		if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new IncorrectCredentialsException("Incorrect username or password");
		}

		AuthenticationTokenFactory AuthenticationTokenFactory = new AuthenticationTokenFactory();
		AuthenticationToken authenticationToken = AuthenticationTokenFactory.create();

		authenticationDatabaseConnector.createAuthenticationToken(authenticationToken);
		
		return authenticationToken;
	}

	@Override
	public void verifyToken(String token) {
		AuthenticationToken authenticationToken = authenticationDatabaseConnector.getAuthenticationToken(token);
		if(authenticationToken == null) throw new IncorrectAuthenticationTokenException("Provided token is invalid");
	}
}