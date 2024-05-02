package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	public CategoryServiceImpl service;
	
	// create category 
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto){
		
		return new ResponseEntity<CategoryDto>(service.createCategory(dto),HttpStatus.CREATED);
	}
	
	// get Category By Id 
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
		return new ResponseEntity<CategoryDto>(service.getCategoryById(categoryId),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return new ResponseEntity<List<CategoryDto>>(service.getAllCategories(),HttpStatus.OK);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto , @PathVariable Integer categoryId){
		return new ResponseEntity<CategoryDto>(service.updateCategory(dto, categoryId),HttpStatus.OK);
	}
	@DeleteMapping("/{categoryId}") 
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		service.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	
	
}









