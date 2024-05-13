package com.blog.services;

import com.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto dto, Integer postId);
	void deleteComment(Integer commentid) ;
}
