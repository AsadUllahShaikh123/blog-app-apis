package com.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postBody, @PathVariable Integer userId, @PathVariable Integer categoryId ) {
		PostDto postDto = postService.createPost(postBody, userId, categoryId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId ){
		
		PostDto getPost = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(getPost,HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy
			){
		PostResponse posts =  postService.getAllPosts(pageNumber, pageSize,sortBy);
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
	
		List<PostDto> posts = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts = postService.getPostsByUser(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully ",true),HttpStatus.OK);
	}
	
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post , @PathVariable Integer postId){
		
		PostDto dto = postService.updatePost(post, postId);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keywords){
		
		List<PostDto> posts = postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException{
		PostDto dto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, image);
		dto.setImageName(fileName);
		PostDto updatePost = postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	// method to serve files
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException{
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
}
