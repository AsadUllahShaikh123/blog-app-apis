package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Post post = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = repo.save(post);
		PostDto dto = modelMapper.map(updatedPost, PostDto.class);
		return dto;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		repo.delete(post);
	}

	@Override
	public List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize) {
	
		    Pageable p = PageRequest.of(pageNumber, pageSize);
			Page<Post> allPosts= repo.findAll(p);
			List<Post> posts = allPosts.getContent();
			List<PostDto> dtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post ", "postId", id));
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
