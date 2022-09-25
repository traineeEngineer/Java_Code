package com.ExamTest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ExamTest.model.Movie;
import com.ExamTest.repositories.MovieRepository;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

	@Mock
	private MovieRepository movieRepo;

	@InjectMocks
	private MovieService service;

	private Movie avatar;
	private Movie godzilla;

	@BeforeEach
	void setUp() {
		avatar = new Movie();
		avatar.setId(1L);
		avatar.setName("Avatar");
		avatar.setGenera("Action");
		avatar.setReleaseDate(LocalDate.of(2000, Month.JULY, 11));

		godzilla = new Movie();
		godzilla.setId(2L);
		godzilla.setName("Godzilla");
		godzilla.setGenera("Action");
		godzilla.setReleaseDate(LocalDate.of(2021, Month.AUGUST, 10));

	}

	@Test
	void testsaveMovie() {

		when(movieRepo.save(any(Movie.class))).thenReturn(avatar);

		Movie newMovie = service.saveMovie(avatar);
		assertNotNull(newMovie);
		assertThat(newMovie.getName()).isEqualTo("Avatar");
	}

	@Test
	void getAllMovies() {

		List<Movie> list = new ArrayList<>();
		list.add(godzilla);
		list.add(avatar);

		when(movieRepo.findAll()).thenReturn(list);

		List<Movie> existMovie = service.getAllMovie();

		assertNotNull(existMovie);
		assertEquals(2, list.size());
	}

	@Test
	void getMoviesById() {

		when(movieRepo.findById(anyLong())).thenReturn(Optional.of(avatar));

		Movie existMovie = service.getMovieById(1L);
		assertNotNull(existMovie);
		assertThat(existMovie.getId()).isEqualTo(1L);
	}

	@Test
	void getMovieByIdWithException() {

		when(movieRepo.findById(1L)).thenReturn(Optional.of(avatar));

		assertThrows(RuntimeException.class, () -> service.getMovieById(2L));
	}

	@Test
	void testUpdateMovie() {

		when(movieRepo.findById(anyLong())).thenReturn(Optional.of(avatar));
		when(movieRepo.save(any(Movie.class))).thenReturn(avatar);

		avatar.setGenera("scientific");

		Movie updateMovie = service.updateMovie(avatar, 1L);
		assertNotNull(updateMovie);
		assertEquals("scientific", updateMovie.getGenera());
	}

	@Test
	void deleteMovies() {

		when(movieRepo.findById(anyLong())).thenReturn(Optional.of(avatar));
		doNothing().when(movieRepo).delete(any(Movie.class));

		service.deleteMovie(1L);

		verify(movieRepo, times(1)).delete(avatar);
	}

}
