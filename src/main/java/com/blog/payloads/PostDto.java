package com.blog.payloads;

import java.util.Date;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.Data;

@Data
public class PostDto {

	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private Category category;
	private User user;
}
