package com.blog.services;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostService {

	// create Post 
	Post createPost(Post post);
	
	// update Post 
	Post updatePost(Post post , Integer id);
	
	// delete Post 
	void deletePost(Integer id);
	
	// getAll Posts 
	List<Post> getAllPosts();
	
	// getPost By ID 
	Post getPostById(Integer id);
	
	// getAllPostByCategory 
	List<Post> getPostsByCategory(Category category);
	
	// getAllPostByUser 
	List<Post> getPostsByUser(User user);
	
	// search Posts 
	List<Post> searchPost(String keyword);
}
