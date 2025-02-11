package com.groupeisi.keycloak.service;

import com.groupeisi.keycloak.dao.IUserDao;
import com.groupeisi.keycloak.dao.UserDao;
import com.groupeisi.keycloak.dto.UserDto;
import com.groupeisi.keycloak.entity.UserEntity;
import com.groupeisi.keycloak.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

	private IUserDao userDao = new UserDao();
	
	/**
	 * Cette méthode permet de vérifier les identifiants d'un utilisateur à partir de son mail et de son mot de passe.
	 * @email : représente l'email de l'utilisateur.
	 * @password : représente le mot de passe de l'utilisateur.
	 * La fonction retourne un Optional de UserDto qui peut être vide ou pas.
	 */
	@Override
	public Optional<UserDto> login(String email, String password) {

		if(email.isBlank() || password.isBlank()) {
			return Optional.empty();
		}
		else {
			return testLogin(email, password);
		}
	}

	private Optional<UserDto> testLogin(String email, String password) {
		
		Optional<UserEntity> userEntity = userDao.login(email, password);

		if(userEntity.isPresent()) {
			UserDto userDto = UserMapper.toUserDto(userEntity.get());
			return Optional.of(userDto);
		}
		else {
			return Optional.empty();
		}
	}


}
