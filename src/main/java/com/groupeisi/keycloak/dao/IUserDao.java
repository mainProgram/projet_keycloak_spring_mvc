package com.groupeisi.keycloak.dao;

import com.groupeisi.keycloak.entity.UserEntity;

import java.util.Optional;

public interface IUserDao {

    Optional<UserEntity> login(String username, String password);

    Optional<UserEntity> findByUsername(String username);

}
