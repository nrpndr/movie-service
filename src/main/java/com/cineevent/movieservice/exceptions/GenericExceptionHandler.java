package com.cineevent.movieservice.exceptions;

import org.springframework.http.HttpStatus;

import com.cineevent.movieservice.dto.request.ErrorDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class GenericExceptionHandler {
	
	private GenericExceptionHandler() {
		throw new IllegalStateException("Tranformer class");
	}
	
	public static ErrorDTO handleException(Exception e) {
		log.error("handleException", e);
		if(e instanceof InValidAccessTokenException) {
			return new ErrorDTO(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		} else if(e instanceof AccessTokenExpiredException) {
			return new ErrorDTO(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		} else if(e instanceof MissingAuthorizationHeaderException) {
			return new ErrorDTO(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		} else {
			return new ErrorDTO("UnKnown Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
