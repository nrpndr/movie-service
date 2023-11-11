package com.cineevent.movieservice.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import com.cineevent.movieservice.dto.request.MovieRequestDTO;
import com.cineevent.movieservice.dto.response.MovieResponseDTO;
import com.cineevent.movieservice.model.Movie;

public final class MovieDTOTransformer {
	
	private MovieDTOTransformer() {
		 throw new IllegalStateException("Tranformer class");
	}
	
	public static Movie transformToMovie(MovieRequestDTO movieRequestDTO) {
		Movie movie = new Movie();
		
		String cast = String.join(",",movieRequestDTO.getCast());
		
		movie.setCast(cast);
		movie.setDescription(movieRequestDTO.getDescription());
		movie.setReleaseDate(movieRequestDTO.getReleaseDate());
		
		movie.setGenre(movieRequestDTO.getGenre());
		movie.setTitle(movieRequestDTO.getTitle());
		movie.setDirector(movieRequestDTO.getDirector());
		
		movie.setMovieDurationInMinutes(Integer.parseInt(movieRequestDTO.getMovieDurationInMinutes()));
		
		return movie;
	}

	public static MovieResponseDTO transformToMovieResponseDTO(Movie movie) {
		MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
		
		movieResponseDTO.setMovieId(movie.getId());
		
		String[] castNamesList = movie.getCast().split(",");
		List<String> castNames = new ArrayList<>();
		for(String castName : castNamesList) {
			castNames.add(castName);
		}
		movieResponseDTO.setCast(castNames);
		movieResponseDTO.setDescription(movie.getDescription());
		
		movieResponseDTO.setReleaseDate(movie.getReleaseDate());
		movieResponseDTO.setGenre(movie.getGenre());
		movieResponseDTO.setDirector(movie.getDirector());
		
		movieResponseDTO.setTitle(movie.getTitle());
		movieResponseDTO.setMovieDurationInMinutes(movie.getMovieDurationInMinutes());
		
		return movieResponseDTO;
	}

	public static void updateMovieFromDB(Movie movieFromDB, MovieRequestDTO movieRequestDTO) {
		
		if(!CollectionUtils.isEmpty(movieRequestDTO.getCast()) ) {
			String castNames = String.join(",",movieRequestDTO.getCast());
			movieFromDB.setCast(castNames);
		}
		
		if(!Strings.isBlank(movieRequestDTO.getDescription())) {
			movieFromDB.setDescription(movieRequestDTO.getDescription());
		}
		
		if(!Strings.isBlank(movieRequestDTO.getReleaseDate())) {
			movieFromDB.setReleaseDate(movieRequestDTO.getReleaseDate());
		}
		
		if(!Strings.isBlank(movieRequestDTO.getGenre())) {
			movieFromDB.setGenre(movieRequestDTO.getGenre());
		}

		if(!Strings.isBlank(movieRequestDTO.getTitle())) {
			movieFromDB.setTitle(movieRequestDTO.getTitle());
		}
		
		if(!Strings.isBlank(movieRequestDTO.getDirector())) {
			movieFromDB.setDirector(movieRequestDTO.getDirector());
		}
		
		String movieDurationInMinutes = movieRequestDTO.getMovieDurationInMinutes();
		if(!Strings.isBlank(movieDurationInMinutes) && Integer.parseInt(movieDurationInMinutes) > 0) {
			movieFromDB.setMovieDurationInMinutes(Integer.parseInt(movieDurationInMinutes));
		}
	}

}
