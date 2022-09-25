package com.ExamTest.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.ExamTest.model.Movie;
import com.ExamTest.repositories.MovieRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieIntegrateTest {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private MovieRepository repo;

	private Movie avatar;
	private Movie godzilla;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void beforeSetup() {
		baseUrl = baseUrl + ":" + port + "/movies";

		avatar = new Movie();
		avatar.setName("Avatar");
		avatar.setGenera("action");
		avatar.setReleaseDate(LocalDate.of(2002, Month.APRIL, 13));

		godzilla = new Movie();
		godzilla.setName("GodZilla");
		godzilla.setGenera("action");
		godzilla.setReleaseDate(LocalDate.of(2022, Month.JUNE, 23));

		avatar = repo.save(avatar);
		godzilla = repo.save(godzilla);

	}

	@AfterEach
	public void afterSetup() {
		repo.deleteAll();
	}

	@Test
	void testCreateMovie() {

		Movie newMovie = restTemplate.postForObject(baseUrl, avatar, Movie.class);
		assertNotNull(newMovie);
		assertThat(newMovie.getId()).isNotNull();

	}

	@Test
	void testFetchMovies() {

		List<Movie> list = restTemplate.getForObject(baseUrl, List.class);

		assertNotNull(list);
		assertThat(list.size()).isEqualTo(2);

	}

	@Test
	void testFetchOneMovie() {

		Movie existMovie = restTemplate.getForObject(baseUrl + "/" + avatar.getId(), Movie.class);

		assertNotNull(existMovie);
		assertEquals("Avatar", existMovie.getName());
	}

	@Test
	void testDeleteOneMovie() {
		
		restTemplate.delete(baseUrl + "/" + godzilla.getId());

		int count = repo.findAll().size();
		assertEquals(1, count);
	}

	@Test
	void testUpdateMovie() {
		
		godzilla.setGenera("adventure");

		restTemplate.put(baseUrl + "/{id}", godzilla, godzilla.getId());

		Movie exist = restTemplate.getForObject(baseUrl + "/" + godzilla.getId(), Movie.class);

		assertNotNull(exist);
		assertEquals("adventure", exist.getGenera());
	}
}
