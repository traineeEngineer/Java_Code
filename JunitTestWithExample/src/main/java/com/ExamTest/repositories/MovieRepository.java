package com.ExamTest.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ExamTest.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>  {
	
	
	List<Movie>findByGenera(String genera);
}
