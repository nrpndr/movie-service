package com.cineevent.movieservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cineevent.movieservice.dto.request.MovieRequestDTO;
import com.cineevent.movieservice.dto.response.MovieResponseDTO;
import com.cineevent.movieservice.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public List<MovieResponseDTO> getAllMovies() {
		return movieService.getAllMovies();
	}

	@GetMapping("/{movieId}")
	public MovieResponseDTO getMovie(@PathVariable("movieId") int movieId) {
		return movieService.getMovie(movieId);
	}

	@Operation(security = { @SecurityRequirement (name = "bearer-key") })
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
		return new ResponseEntity<>(movieService.createMovie(movieRequestDTO), null, HttpStatus.CREATED);
	}

	@Operation(security = { @SecurityRequirement (name = "bearer-key") })
	@PutMapping("/{movieId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MovieResponseDTO createOrUpdateMovie(@PathVariable("movieId") int movieId,
			@RequestBody MovieRequestDTO movieRequestDTO) {
		return movieService.createOrUpdateMovie(movieId, movieRequestDTO);
	}
	
	@Operation(security = { @SecurityRequirement (name = "bearer-key") })
	@PatchMapping("/{movieId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MovieResponseDTO updateMovie(@PathVariable("movieId") int movieId,
			@RequestBody MovieRequestDTO movieRequestDTO) {
		return movieService.updateMovie(movieId, movieRequestDTO);
	}

	@Operation(security = { @SecurityRequirement (name = "bearer-key") })
	@DeleteMapping("/{movieId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int movieId) {
		movieService.deleteMovieById(movieId);
		return ResponseEntity.noContent().build();
	}

}
