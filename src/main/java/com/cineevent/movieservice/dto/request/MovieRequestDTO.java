package com.cineevent.movieservice.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieRequestDTO {
	 
	 private String title;
	 
	 private String releaseDate;
	 
	 private String genre;
	 
	 private String movieDurationInMinutes;
	 
	 private String director;
	 
	 private String description;
	 
	 private List<String> cast;
	 
}
