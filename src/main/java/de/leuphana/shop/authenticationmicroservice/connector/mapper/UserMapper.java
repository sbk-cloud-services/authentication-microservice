package de.leuphana.shop.authenticationmicroservice.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.shop.authenticationmicroservice.component.structure.User;
import de.leuphana.shop.authenticationmicroservice.connector.entity.UserEntity;

public class UserMapper {
    private static Mapper mapper;
    
    static {
        mapper = DozerBeanMapperSingletonWrapper.getInstance();
    }

    public static UserEntity mapUserToUserEntity(User user) {
        return mapper.map(user, UserEntity.class);
    }

    public static User mapUserEntityToUser(UserEntity userEntity) {
        return mapper.map(userEntity, User.class);
    }
}
