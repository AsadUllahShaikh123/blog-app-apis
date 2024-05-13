package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositiries.CommentRepo;
import com.blog.repositiries.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentRepo commentService;
	
	@Autowired 
	ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = commentService.save(comment);
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentService.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId ", commentId));
		commentService.delete(comment);
	}

}
