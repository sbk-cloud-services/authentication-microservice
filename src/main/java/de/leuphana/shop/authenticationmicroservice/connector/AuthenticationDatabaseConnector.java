package de.leuphana.shop.authenticationmicroservice.connector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.component.structure.User;
import de.leuphana.shop.authenticationmicroservice.connector.entity.AuthenticationTokenEntity;
import de.leuphana.shop.authenticationmicroservice.connector.entity.UserEntity;
import de.leuphana.shop.authenticationmicroservice.connector.mapper.AuthenticationTokenMapper;
import de.leuphana.shop.authenticationmicroservice.connector.mapper.UserMapper;

public class AuthenticationDatabaseConnector {
    private EntityManager entityManager;

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public User getUser(String email) {
        UserEntity userEntity = entityManager.createQuery("FROM user WHERE email = :email", UserEntity.class).setParameter("email", email).getSingleResult();
        return UserMapper.mapUserEntityToUser(userEntity);
    }

    @Transactional
	public AuthenticationToken createAuthenticationToken(AuthenticationToken authenticationToken) {
        AuthenticationTokenEntity authenticationTokenEntity = AuthenticationTokenMapper.mapAuthenticationTokenToAuthenticationTokenEntity(authenticationToken);
        entityManager.persist(authenticationTokenEntity);
        return authenticationToken;
	}

    @Transactional
	public AuthenticationToken getAuthenticationToken(String token) {
        AuthenticationTokenEntity authenticationTokenEntity = entityManager.find(AuthenticationTokenEntity.class, token);
        return AuthenticationTokenMapper.mapAuthenticationTokenEntityToAuthenticationToken(authenticationTokenEntity);
	}
}
