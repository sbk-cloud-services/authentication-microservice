package de.leuphana.shop.authenticationmicroservice.component.behaviour;

import org.springframework.security.crypto.password.PasswordEncoder;

import de.leuphana.shop.authenticationmicroservice.component.behaviour.exception.IncorrectAuthenticationTokenException;
import de.leuphana.shop.authenticationmicroservice.component.behaviour.exception.IncorrectCredentialsException;
import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationTokenManager;
import de.leuphana.shop.authenticationmicroservice.component.structure.User;
import de.leuphana.shop.authenticationmicroservice.connector.AuthenticationDatabaseConnector;

public class AuthenticationServiceImplementation implements AuthenticationService {

	private AuthenticationDatabaseConnector authenticationDatabaseConnector;
	private PasswordEncoder passwordEncoder;
	private AuthenticationTokenManager authenticationTokenManager;

	public AuthenticationServiceImplementation(AuthenticationDatabaseConnector authenticationDatabaseConnector,
			PasswordEncoder passwordEncoder, AuthenticationTokenManager authenticationTokenManager) {
		this.authenticationDatabaseConnector = authenticationDatabaseConnector;
		this.passwordEncoder = passwordEncoder;
		this.authenticationTokenManager = authenticationTokenManager;
	}

	public AuthenticationToken authenticate(String email, String password) throws IncorrectCredentialsException {
		User user = authenticationDatabaseConnector.getUser(email);

		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new IncorrectCredentialsException("Incorrect username or password");
		}

		return authenticationTokenManager.create(user);
	}

	@Override
	public void verifyToken(String token) throws IncorrectAuthenticationTokenException {
		authenticationTokenManager.verify(token);
	}
}