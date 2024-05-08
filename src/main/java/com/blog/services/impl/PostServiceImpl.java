package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositiries.CategoryRepo;
import com.blog.repositiries.PostRepo;
import com.blog.repositiries.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo repo;
	@Autowired 
	private ModelMapper modelMapper;
	@Autowired 
	private UserRepo userRepo;
	@Autowired 
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ","userId ", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("image.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = repo.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {

		
		
	}

	@Override
	public List<PostDto> getAllPosts() {
			List<Post> posts = repo.findAll();
			List<PostDto> dtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post ", "postId", id));
//	    Integer userId = getPost.getUser().getId();
//		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "userId", userId));
//		Integer categoryId = getPost.getCategory().getCategoryId();
//		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
	
		PostDto dto = modelMapper.map(post, PostDto.class);
		return dto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category= categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "categoryId", categoryId));
		List<Post> posts = repo.findByCategory(category);
		
		List<PostDto> dtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User users = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "userId", userId));
		
		List<Post> posts =repo.findByUser(users);
		List<PostDto> dtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
