package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Posts;

public interface PostRepository extends JpaRepository<Posts, Long> {

}
