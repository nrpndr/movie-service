package com.cineevent.movieservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name="title", nullable = false)
	private String title;

	@Column(name="release_date", nullable = false)
	private String releaseDate;

	@Column(name="genre", nullable = false)
	private String genre;

	@Column(name="movie_duration_in_minutes", nullable = false)
	private int movieDurationInMinutes;

	@Column(name="director", nullable = false)
	private String director;

	@Column(name="description", nullable = false)
	private String description;

	@Column(name="cast", nullable = false)
	private String cast;

}
