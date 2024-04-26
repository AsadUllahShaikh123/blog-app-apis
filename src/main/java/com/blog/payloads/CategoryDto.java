package com.blog.payloads;

import lombok.Data;

@Data
public class CategoryDto {

	private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;
}
