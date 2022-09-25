package com.ExamTest.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ExamTest.model.Movie;
import com.ExamTest.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class MovieControllerTest {

	@MockBean
	private MovieService service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private Movie avatar;
	private Movie godzilla;
	
	@BeforeEach
	void setUp() {
		avatar = new Movie();
		avatar.setId(1L);
		avatar.setName("Avatar");
		avatar.setGenera("Action");
		avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 7));

		godzilla = new Movie();
		godzilla.setId(2L);
		godzilla.setName("Godzilla");
		godzilla.setGenera("Action");
		godzilla.setReleaseDate(LocalDate.of(2021, Month.APRIL, 9));
	}
	
	
	
	@Test
	void testSaveOperation() throws Exception {
		when(service.saveMovie(any(Movie.class))).thenReturn(avatar);
		this.mockMvc
				.perform(post("/movies").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(avatar)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(avatar.getName())))
				.andExpect(jsonPath("$.genera", is(avatar.getGenera())))
				.andExpect(jsonPath("$.releaseDate", is(avatar.getReleaseDate().toString())));
	}

	@Test
	void testfetchAllMovies() throws Exception {

		List<Movie> list = new ArrayList<>();
		list.add(godzilla);
		list.add(avatar);

		when(service.getAllMovie()).thenReturn(list);
		this.mockMvc.perform(get("/movies"))
		            .andExpect(status().isOk())
				    .andExpect(jsonPath("$.size()", is(list.size())));
	}
	
	
	@Test
	void testfetchMoviesById() throws Exception {
		
		when(service.getMovieById(anyLong())).thenReturn(avatar);
		
		this.mockMvc.perform(get("/movies/{id}",1L))
		            .andExpect(status().isOk())
		           .andExpect(jsonPath("$.name", is(avatar.getName())))
		           .andExpect(jsonPath("$.genera", is(avatar.getGenera())));
	}      
	
	@Test
	void testDeleteMovie() throws Exception {
		
		doNothing().when(service).deleteMovie(anyLong());
		
		this.mockMvc.perform(delete("/movies/{id}",1L))
		            .andExpect(status()
		            .isNoContent());
	}
	
	@Test
	void testupdateMovies() throws JsonProcessingException, Exception {
		
		when(service.updateMovie(any(Movie.class), anyLong())).thenReturn(avatar);
		
		this.mockMvc.perform(put("/movies/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(avatar)))
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.name", is(avatar.getName())))
		 .andExpect(jsonPath("$.genera", is(avatar.getGenera())));
	}
}
