package com.spring.springbootmongoDb.controller;

import com.spring.springbootmongoDb.model.JobPost;
import com.spring.springbootmongoDb.repositroy.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository repo;

    @GetMapping("/posts")
    public List<JobPost> getAllPost(){
        return repo.findAll();
    }

    @PostMapping("/savePost")
    public JobPost savePost(@RequestBody JobPost post){
        return  repo.save(post);
    }

}
