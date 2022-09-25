package com.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.Utils.AppConstants;
import com.CRUD.payLoad.PostDTO;
import com.CRUD.payLoad.PostResponse;
import com.CRUD.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	
	@Autowired
	private PostService service;
	
	
	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO dto){
		return new ResponseEntity(service.createPost(dto),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public PostResponse getAllPost(@RequestParam(value="pageNo",defaultValue =AppConstants.DEFAULT_PAGE_NO )int pageNo,
			@RequestParam(value="pageSize",defaultValue =AppConstants.DEFAULT_PAGE_SIZE )int pageSize,
			@RequestParam(value="sortBy",defaultValue =AppConstants.DEFALUT_SORT_BY )String sortBy,
			@RequestParam(value="sortDir",defaultValue =AppConstants.DEFALUT_SORT_DIRECTION )String sortDir) {
		
		return service.getAllPost(pageNo, pageSize, sortBy, sortDir);
	}
	
}
