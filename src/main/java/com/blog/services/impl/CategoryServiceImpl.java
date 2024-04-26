package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositiries.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired 
	public CategoryRepo repo;
	@Autowired
	public ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = repo.save(category);
		
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category foundCategory = repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		
		foundCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		foundCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = repo.save(foundCategory);		
		
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id" , categoryId) );
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category foundCategory = repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		return modelMapper.map(foundCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> allCategories = repo.findAll();
		List<CategoryDto> categoryDto = new ArrayList<CategoryDto>();
		allCategories.forEach(category -> categoryDto.add(modelMapper.map(category, CategoryDto.class)));
		return categoryDto;
	}

}
