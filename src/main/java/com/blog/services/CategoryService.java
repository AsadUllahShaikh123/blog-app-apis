package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	// create 
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update 
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	// delete 
	public void deleteCategory(Integer categoryId);
		
	// getCategoryById
	public CategoryDto getCategoryById(Integer categoryId);
	
	// getAllCategories
	public List<CategoryDto> getAllCategories();

}
