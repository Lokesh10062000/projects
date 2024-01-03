package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

public interface CommentsService {
	
	CommentDto createComment(long postId,CommentDto commentDto);

}
