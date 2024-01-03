package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Posts;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}



	@Override
	public PostDto createPost(PostDto postDto) {
		
		Posts post = mapToEntity(postDto);		
		Posts newPost = postRepository.save(post);
		
		PostDto postResponce = mapToDto(newPost);		 
		return postResponce;
	}
	
	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Posts> posts = postRepository.findAll(pageable);
		
		List<Posts> listOfPosts = posts.getContent();
		
		//List<Posts> listOfPosts = postRepository.findAll();
		List<PostDto> content = listOfPosts.stream().map(post ->mapToDto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;
	}
	
	@Override
	public PostDto getPostById(long id) {
	  Posts post = postRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("post", "id", id));
		return mapToDto(post);
	}
	
	//convert entity to dto  
	private PostDto mapToDto(Posts posts) {
		PostDto postDto = new PostDto();
		postDto.setId(posts.getId());
		postDto.setTitile(posts.getTitile());
		postDto.setDescription(posts.getDescription());
		postDto.setContent(posts.getContent());
		return postDto;
	}
	
	//convert dto to entity
	private Posts mapToEntity(PostDto postDto) {
		Posts post = new Posts();
		post.setTitile(postDto.getTitile());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}



	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		 Posts post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		 post.setTitile(postDto.getTitile());
		 post.setDescription(postDto.getDescription());
		 post.setContent(postDto.getContent());
		 
		 Posts updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}



	@Override
	public void deletePostById(long id) {
		 Posts post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		 postRepository.delete(post);
		
	}	

}
