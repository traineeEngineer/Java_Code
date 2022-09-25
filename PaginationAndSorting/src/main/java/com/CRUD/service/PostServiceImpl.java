package com.CRUD.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.CRUD.model.Post;
import com.CRUD.payLoad.PostDTO;
import com.CRUD.payLoad.PostResponse;
import com.CRUD.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository repo;

	@Override
	public PostDTO createPost(PostDTO dto) {
		Post post=maptoEntity(dto);
		Post newPost=repo.save(post);
		
		PostDTO postDto=mapToDTO(newPost);
		return postDto;
	}

	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
		Page<Post>posts=repo.findAll(pageable);
		
		List<Post> listOfPost=posts.getContent();
		List<PostDTO>content=listOfPost.stream().map(post->mapToDTO(post)).collect(Collectors.toList()); 
		
		PostResponse res=new PostResponse();
		res.setContent(content);
		res.setPageNo(posts.getNumber());
		res.setPageSize(posts.getSize());
		res.setTotalElements(posts.getTotalElements());
		res.setTotalPages(posts.getTotalPages());
		res.setLast(posts.isLast());
		return res;
	}
	
	
	private PostDTO mapToDTO(Post newPost) {
		PostDTO postDto=new PostDTO();
			postDto.setId(newPost.getId());
			postDto.setTitle(newPost.getTitle());
			postDto.setDescription(newPost.getDescription());
			postDto.setContent(newPost.getContent());
		return postDto;
	}

	private Post maptoEntity(PostDTO dto) {
		Post post=new Post();
		post.setId(dto.getId());
		post.setTitle(dto.getTitle());
		post.setDescription(dto.getDescription());
		post.setContent(dto.getContent());
		return post;
	}

}
