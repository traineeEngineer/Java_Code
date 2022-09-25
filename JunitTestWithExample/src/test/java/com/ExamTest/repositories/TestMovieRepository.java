package com.ExamTest.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.ExamTest.model.Movie;

@DataJpaTest
class TestMovieRepository {

	@Autowired
	private MovieRepository movieRepository;
	
	private Movie avatarMovie;
	private Movie titanicMovie;

	@BeforeEach
	void init() {
		avatarMovie=new Movie();
		avatarMovie.setName("Avatar");
		avatarMovie.setGenera("Action");
		avatarMovie.setReleaseDate(LocalDate.of(2000, 8, 12));
		
		titanicMovie=new Movie();
		titanicMovie.setName("Titanic");
		titanicMovie.setGenera("Drama");
		titanicMovie.setReleaseDate(LocalDate.of(1997, 5, 21));
	}
	
	@Test
	void testCreateMovie() {
		Movie saveMovie = movieRepository.save(avatarMovie);
		assertNotNull(saveMovie);
		assertThat(saveMovie.getId()).isNotEqualTo(null);
		assertEquals(saveMovie, avatarMovie);
		assertThat(avatarMovie.getReleaseDate()).isAfterOrEqualTo(LocalDate.of(2000, 8, 11));
	}

	@Test
	void testGetAllMovies() {

		movieRepository.save(avatarMovie);

		Movie titanicMovie = new Movie();
		movieRepository.save(titanicMovie);
		List<Movie> listMovies = movieRepository.findAll();
		
		assertNotNull(listMovies);
		assertThat(listMovies).isNotNull();
		assertEquals(2, listMovies.size());
	}

	@Test
	void getMovieById() {
		
		movieRepository.save(avatarMovie);

		Movie existMoview = movieRepository.findById(avatarMovie.getId()).get();
		
		assertNotNull(existMoview);
		assertEquals("Action", existMoview.getGenera());
		assertThat(avatarMovie.getReleaseDate()).isBefore(LocalDate.of(2000, 8, 13));
	}

	@Test
	void testUpdateMovie() {
		
		movieRepository.save(avatarMovie);

		Movie existMovie = movieRepository.findById(avatarMovie.getId()).get();
		existMovie.setGenera("scientific");
		
		Movie newMovie = movieRepository.save(existMovie);

		assertEquals("scientific", newMovie.getGenera());
		assertEquals("Avatar", newMovie.getName());
	}
	
	@Test
	void testDeleteMovie() {
		

		movieRepository.save(avatarMovie);
		
		Long id=avatarMovie.getId();
		
		movieRepository.save(titanicMovie);
		
		movieRepository.delete(avatarMovie);
		Optional<Movie> existMovie = movieRepository.findById(id);
		List<Movie>list= movieRepository.findAll();
		
		assertEquals(1, list.size());
		assertThat(existMovie).isEmpty();
	}
	
	@Test
	void getMovieByGenera() {
		

		movieRepository.save(avatarMovie);
		
		movieRepository.save(titanicMovie);
		
		
	   List<Movie>list= movieRepository.findByGenera("Drama");
	   
	   assertNotNull(list);
	   assertThat(list.size()).isEqualTo(1);
	   
		
		
	}

}
