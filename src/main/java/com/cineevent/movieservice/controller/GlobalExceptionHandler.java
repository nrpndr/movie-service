package com.cineevent.movieservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cineevent.movieservice.dto.response.ErrorResponseDTO;
import com.cineevent.movieservice.exceptions.InValidUserInputException;
import com.cineevent.movieservice.exceptions.MovieDoesNotExistException;
import com.cineevent.movieservice.exceptions.ServiceCommunicationException;
import com.cineevent.movieservice.exceptions.UserDoesNotHavePermissionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({InValidUserInputException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDTO> handleAsBadRequest(RuntimeException ex) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({MovieDoesNotExistException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponseDTO> handleAsNotFound(RuntimeException ex) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({UserDoesNotHavePermissionException.class, AccessDeniedException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponseDTO> handleAsMissingPermssion(RuntimeException ex) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler({ServiceCommunicationException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseDTO> handleServiceCommunicationException(RuntimeException ex) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseDTO> handleRemainingException(Exception ex) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Unknown Internal Server Error");
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
