package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentsService;

@RestController
public class CommentController {
	
	private CommentsService commentsService;
	
	public CommentController(CommentsService commentsService) {
		super();
		this.commentsService = commentsService;
	}



	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId,
			                                        @RequestBody CommentDto commentDto){
		
		return new ResponseEntity<>(commentsService.createComment(postId, commentDto),HttpStatus.CREATED);
		
	}

}
