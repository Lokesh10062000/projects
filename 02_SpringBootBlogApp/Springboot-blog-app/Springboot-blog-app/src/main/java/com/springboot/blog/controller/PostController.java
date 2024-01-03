package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstraints;

@RestController
@RequestMapping("/api/v1")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping("/createPost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo", defaultValue = AppConstraints.PAGE_NO, required = false) int pageNO,
			@RequestParam(value="pageSize", defaultValue = AppConstraints.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = AppConstraints.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue = AppConstraints.SORT_DIR, required = false) String sortDir
			){
		return postService.getAllPosts(pageNO, pageSize , sortBy ,sortDir);
		//return postService.getAllPosts(0, 0);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name="id") long id){
		return new ResponseEntity<>(postService.updatePost(postDto, id),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePostBYId(@PathVariable(name="id") long id) {
		postService.deletePostById(id);
		return new ResponseEntity<>("post deleted successfully", HttpStatus.OK);
	}

}
