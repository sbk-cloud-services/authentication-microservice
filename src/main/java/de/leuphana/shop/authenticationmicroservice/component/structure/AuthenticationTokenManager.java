package de.leuphana.shop.authenticationmicroservice.component.structure;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.Jwts;

public class AuthenticationTokenManager {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public AuthenticationTokenManager(String privateKeyFilePath, String publicKeyFilePath) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(privateKeyFilePath);
        byte[] privateKeyBytes = inputStream.readAllBytes();

        inputStream = getClass().getClassLoader().getResourceAsStream(publicKeyFilePath);
        byte[] publicKeyBytes = inputStream.readAllBytes();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        this.privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        this.publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

	public AuthenticationToken create(User user) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + 30 * 60 * 60 * 1000); // expire after 30 minutes

        String token = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getId().toString())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(privateKey)
                .compact();
        
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);

        return authenticationToken;
	}

	public void verify(String token) throws IncorrectAuthenticationTokenException {
        if(!Jwts.parserBuilder().setSigningKey(publicKey).build().isSigned(token)) {
            throw new IncorrectAuthenticationTokenException("Token is not valid");
        }
	}
}
