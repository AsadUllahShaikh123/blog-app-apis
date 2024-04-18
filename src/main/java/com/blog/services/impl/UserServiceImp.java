package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
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
		User foundUser = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		foundUser.setName(user.getName());
		foundUser.setEmail(user.getEmail());
		foundUser.setPassword(user.getPassword());
		foundUser.setAbout(user.getAbout());
		
		User updatedUser = repo.save(foundUser);
		
		return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User foundUser = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return userToDto(foundUser);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = repo.findAll();
		List<UserDto> userDto = new ArrayList<UserDto>();
		allUsers.forEach(user -> userDto.add(userToDto(user)));
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		repo.delete(user);
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
