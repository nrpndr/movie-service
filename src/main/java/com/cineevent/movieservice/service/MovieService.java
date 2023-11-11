package com.cineevent.movieservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cineevent.movieservice.dto.request.MovieRequestDTO;
import com.cineevent.movieservice.dto.response.MovieResponseDTO;
import com.cineevent.movieservice.exceptions.InValidUserInputException;
import com.cineevent.movieservice.exceptions.MovieDoesNotExistException;
import com.cineevent.movieservice.model.Movie;
import com.cineevent.movieservice.repository.MovieRepository;
import com.cineevent.movieservice.transformer.MovieDTOTransformer;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
	   
	   validateInput(movieRequestDTO, false);
	   Movie movie = MovieDTOTransformer.transformToMovie(movieRequestDTO);
	   movieRepository.save(movie);
	   
	   return MovieDTOTransformer.transformToMovieResponseDTO(movie);
	}
	
	public MovieResponseDTO createOrUpdateMovie(int movieId, MovieRequestDTO movieRequestDTO) {
		validateInput(movieRequestDTO, false);
		
		Movie movie = movieRepository.findById(movieId).orElse(null);
		
		if(movie != null) {
			//Updating the movie
			MovieDTOTransformer.updateMovieFromDB(movie, movieRequestDTO);
		}else {
			//Creating an movie
			movie = MovieDTOTransformer.transformToMovie(movieRequestDTO);
			movie.setId(movieId);
		}
		
		movieRepository.save(movie);

		return MovieDTOTransformer.transformToMovieResponseDTO(movie);
	}
	
	public MovieResponseDTO updateMovie(int movieId, MovieRequestDTO movieRequestDTO) {
		validateInput(movieRequestDTO, true);
		
		Movie movieFromDB = movieRepository.findById(movieId).orElse(null);
		if(movieFromDB == null) {
			throw constructMovieDoesNotExistException(movieId);
		}
		
		MovieDTOTransformer.updateMovieFromDB(movieFromDB, movieRequestDTO);
		movieRepository.save(movieFromDB);
		return MovieDTOTransformer.transformToMovieResponseDTO(movieFromDB);
	}
	
	private void validateInput(MovieRequestDTO movieRequestDTO, boolean isPartialUpdate) {
		
		if (movieRequestDTO == null) {
			throw new InValidUserInputException("MovieRequest Input cannot be null");
		}
		
		String movieTitle = movieRequestDTO.getTitle();
		String movieDescription = movieRequestDTO.getDescription();
		String genre = movieRequestDTO.getGenre();
		List<String> cast = movieRequestDTO.getCast();
		String movieDurationInMinutes =  movieRequestDTO.getMovieDurationInMinutes();
		
		String releaseDate = movieRequestDTO.getReleaseDate();
		String director = movieRequestDTO.getDirector();

		if(Strings.isBlank(movieTitle) && !isPartialUpdate) {
			throw new InValidUserInputException("title is missing in input");
		}
		
		if(Strings.isBlank(movieDescription) && !isPartialUpdate) {
			throw new InValidUserInputException("description is missing in input");
		}
		
		if(Strings.isBlank(genre) && !isPartialUpdate) {
			throw new InValidUserInputException("genre is missing in input");
		}
		
		if(CollectionUtils.isEmpty(cast) && !isPartialUpdate) {
			throw new InValidUserInputException("cast is missing in input");
		}
		
		if(Strings.isBlank(movieDurationInMinutes) && !isPartialUpdate) {
			throw new InValidUserInputException("movieDurationInMinutes value should be int, greater than 0");
		}
		
		if(!Strings.isBlank(movieDurationInMinutes)) {
			validateMovieDurationInMinutes(movieDurationInMinutes);
		}
		
		if(Strings.isBlank(releaseDate) && !isPartialUpdate) {
			throw new InValidUserInputException("releaseDate is missing in input");
		}
		
		if(!Strings.isBlank(releaseDate)) {
			validateDate(releaseDate);
		}
		
		if(Strings.isBlank(director) && !isPartialUpdate) {
			throw new InValidUserInputException("director is missing in input");
		}
		
	}

	private void validateMovieDurationInMinutes(String movieDurationInMinutes) {
		try {
			int duration = Integer.parseInt(movieDurationInMinutes);
			if(duration < 0) {
				throw new InValidUserInputException("movieDurationInMinutes value should be int, greater than 0");
			}
		}catch (NumberFormatException e) {
			throw new InValidUserInputException("movieDurationInMinutes value should be int, greater than 0");
		}
		
	}

	private void validateDate(String releaseDate) {
		try {
			new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);  
		} catch (ParseException e) {
			throw new InValidUserInputException("releaseDate should be in format dd/MM/yyyy");
		}
	}

	public List<MovieResponseDTO> getAllMovies() {
		List<MovieResponseDTO> movieResponseDTOs = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		if(!CollectionUtils.isEmpty(movies)) {
			for(Movie movie : movies) {
				movieResponseDTOs.add(MovieDTOTransformer.transformToMovieResponseDTO(movie));
			}
		}
		return movieResponseDTOs;
	}

	public MovieResponseDTO getMovie(int movieId) {
		Movie movie = movieRepository.findById(movieId).orElse(null);
		if(movie != null) {
			return MovieDTOTransformer.transformToMovieResponseDTO(movie);
		}else {
			throw constructMovieDoesNotExistException(movieId);
		}
	}

	public void deleteMovieById(int movieId) {
		log.info("deleteMovieById:: movie Id {}", movieId);
		Movie movie = movieRepository.findById(movieId).orElse(null);
		if(movie != null) {
			movieRepository.deleteById(movieId);
		}else {
			throw constructMovieDoesNotExistException(movieId);
		}
		log.info("Movie with id {} has been deleted.", movieId);
	}
	
	private MovieDoesNotExistException constructMovieDoesNotExistException(int movieId) {
		String msg = String.format("There is no movie with id %s", movieId);
		return new MovieDoesNotExistException(msg);
	}
}
