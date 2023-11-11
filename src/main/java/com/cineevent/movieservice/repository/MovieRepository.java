package com.cineevent.movieservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cineevent.movieservice.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
