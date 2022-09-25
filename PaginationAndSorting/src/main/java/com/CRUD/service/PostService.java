package com.CRUD.service;

import com.CRUD.payLoad.PostDTO;
import com.CRUD.payLoad.PostResponse;

public interface PostService {
	PostDTO createPost(PostDTO dto);
	
	PostResponse getAllPost(int pagNo, int pageSize,String sortBy, String sortDir);
}
