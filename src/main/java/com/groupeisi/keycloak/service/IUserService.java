package com.groupeisi.keycloak.service;

import com.groupeisi.keycloak.dto.UserDto;

import java.util.Optional;

public interface IUserService {

	Optional<UserDto> login(String email, String password);

}
