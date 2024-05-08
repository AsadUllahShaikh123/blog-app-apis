package com.blog.services;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.User;
import com.blog.payloads.PostDto;

public interface PostService {

	// create Post 
	PostDto createPost(PostDto post,Integer userId, Integer categoryId);
	
	// update Post 
	PostDto updatePost(PostDto post , Integer id);
	
	// delete Post 
	void deletePost(Integer id);
	
	// getAll Posts 
	List<PostDto> getAllPosts();
	
	// getPost By ID 
	PostDto getPostById(Integer id);
	
	// getAllPostByCategory 
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// getAllPostByUser 
	List<PostDto> getPostsByUser(Integer userId);
	
	// search Posts 
	List<PostDto> searchPost(String keyword);
}
