package com.cineevent.movieservice.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieResponseDTO {
	
	 private int movieId;
	
	 private String title;
	 
	 private String releaseDate;
	 
	 private String genre;
	 
	 private int movieDurationInMinutes;
	 
	 private String director;
	 
	 private String description;
	 
	 private List<String> cast;

}
