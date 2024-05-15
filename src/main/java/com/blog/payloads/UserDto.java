package com.blog.payloads;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min= 4, message="Username must contain atleast 4 characters")
	private String name;
	@Email(message="Email address is not valid! ")
	private String email;
	@NotNull
	@Size(min = 3 , max = 10 , message ="Username must contain 3 characters and shouldn't exceed 10")
	private String password;
	@NotNull
	private String about;
}
