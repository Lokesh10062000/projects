package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long>{

}
