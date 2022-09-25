package com.ExamTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExamTest.model.Movie;
import com.ExamTest.repositories.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	@Autowired
	private MovieRepository repo;

	public Movie saveMovie(Movie movie) {
		return repo.save(movie);
	}

	public List<Movie> getAllMovie() {
		return repo.findAll();
	}

	public Movie getMovieById(long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Movie Not found"));
	}

	public Movie updateMovie(Movie movie, Long id) {
		Movie existMovie = repo.findById(id).get();
		existMovie.setGenera(movie.getGenera());
		existMovie.setName(movie.getName());
		existMovie.setReleaseDate(movie.getReleaseDate());
		return repo.save(existMovie);
	}

	public void deleteMovie(Long id) {
		Movie existMovie = repo.findById(id).get();
		repo.delete(existMovie);
	}
}
