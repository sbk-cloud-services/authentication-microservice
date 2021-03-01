package de.leuphana.shop.authenticationmicroservice.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.shop.authenticationmicroservice.component.structure.AuthenticationToken;
import de.leuphana.shop.authenticationmicroservice.connector.entity.AuthenticationTokenEntity;

public class AuthenticationTokenMapper {
    private static Mapper mapper;
    
    static {
        mapper = DozerBeanMapperSingletonWrapper.getInstance();
    }

    public static AuthenticationTokenEntity mapAuthenticationTokenToAuthenticationTokenEntity(AuthenticationToken authenticationToken) {
        return mapper.map(authenticationToken, AuthenticationTokenEntity.class);
    }

    public static AuthenticationToken mapAuthenticationTokenEntityToAuthenticationToken(AuthenticationTokenEntity authenticationTokenEntity) {
        return mapper.map(authenticationTokenEntity, AuthenticationToken.class);
    }
}
