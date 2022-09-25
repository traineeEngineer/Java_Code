package com.ExamTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ExamTest.model.Movie;
import com.ExamTest.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movie create(@RequestBody Movie movie) {
		return service.saveMovie(movie);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Movie> read() {
		return service.getAllMovie();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Movie readById(@PathVariable Long id) {
		return service.getMovieById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
		return service.updateMovie(movie, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.deleteMovie(id);
	}

}
