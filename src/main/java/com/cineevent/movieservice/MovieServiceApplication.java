package com.cineevent.movieservice;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MovieServiceApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
