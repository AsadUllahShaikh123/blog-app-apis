package com.blog.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
