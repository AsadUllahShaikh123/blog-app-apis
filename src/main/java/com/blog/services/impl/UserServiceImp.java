package com.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repositiries.UserRepo;
import com.blog.services.UserService;

public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo repo;
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = dtoToUser(userDto);
		User savedUser = repo.save(user);
		
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	public User dtoToUser(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setAbout(user.getAbout());
		
		return dto;
	}
	
	
	
	
	
	
	

}
