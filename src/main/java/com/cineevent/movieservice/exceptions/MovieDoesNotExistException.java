package com.cineevent.movieservice.exceptions;

public class MovieDoesNotExistException extends RuntimeException{

	private static final long serialVersionUID = -255986935176609859L;

	public MovieDoesNotExistException(String message) {
        super(message);
    }
}
