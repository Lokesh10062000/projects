package com.springboot.blog.service.impl;

import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comments;
import com.springboot.blog.entity.Posts;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentsRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentsService;

@Service
public class CommentServiceImpl implements CommentsService{

	private CommentsRepository commentsRepository;
	
	private PostRepository postRepository;
	
	
	public CommentServiceImpl(CommentsRepository commentsRepository, PostRepository postRepository) {
		super();
		this.commentsRepository = commentsRepository;
		this.postRepository = postRepository;
	}



	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Comments comments = mapToEntity(commentDto);
		
		Posts post = postRepository.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("post","id",postId));
		
		comments.setPosts(post);
		Comments newComment = commentsRepository.save(comments);	
		
		return mapToDto(newComment);
	}
	
	//maptoDto
	
	private CommentDto mapToDto(Comments comments) {
		
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comments.getId());
		commentDto.setName(comments.getName());
		commentDto.setEmail(comments.getEmail());
		commentDto.setBody(comments.getBody());	
		return commentDto;
		
	}
	
	//mapToEntity
	
	private Comments mapToEntity(CommentDto commentDto) {
		
		Comments comments = new Comments();
		comments.setId(commentDto.getId());
		comments.setName(commentDto.getName());
		comments.setEmail(commentDto.getEmail());
		comments.setBody(commentDto.getBody());
		
		return comments;
		
	}

}