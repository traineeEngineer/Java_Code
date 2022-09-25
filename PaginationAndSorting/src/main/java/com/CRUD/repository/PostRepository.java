package com.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUD.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
