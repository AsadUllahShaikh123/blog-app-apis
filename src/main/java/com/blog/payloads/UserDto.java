package com.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String about;
}
