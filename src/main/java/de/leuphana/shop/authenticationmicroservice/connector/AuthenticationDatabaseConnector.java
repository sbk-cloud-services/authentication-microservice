package de.leuphana.shop.authenticationmicroservice.connector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import de.leuphana.shop.authenticationmicroservice.component.structure.User;
import de.leuphana.shop.authenticationmicroservice.connector.entity.UserEntity;
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
}
