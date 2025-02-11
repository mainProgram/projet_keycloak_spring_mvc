package com.groupeisi.keycloak.mapper;

import com.groupeisi.keycloak.dto.UserDto;
import com.groupeisi.keycloak.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;


public class UserMapper {

	private UserMapper() {
		
	}

	public static UserEntity toUserEntity(UserDto accountUserDto) {
		
		UserEntity accountUserEntity = new UserEntity();
		
		accountUserEntity.setId(accountUserDto.getId());
		accountUserEntity.setUsername(accountUserDto.getUsername());
		accountUserEntity.setPassword(accountUserDto.getPassword());

		return accountUserEntity;	
	}
	
	public static UserDto toUserDto(UserEntity accountUserEntity) {
		
		UserDto accountUserDto = new UserDto();
		
		accountUserDto.setId(accountUserEntity.getId());
		accountUserDto.setUsername(accountUserEntity.getUsername());
		accountUserDto.setPassword(accountUserEntity.getPassword());

		return accountUserDto;	
	}
	
	public static List<UserDto> toListUserDto(List<UserEntity> accountUserEntities) {
		return accountUserEntities.stream()
							.map(UserMapper::toUserDto)
							.collect(Collectors.toList());
	}	
}
