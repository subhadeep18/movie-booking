package com.yorbit.moviebooking.handler;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yorbit.moviebooking.exception.BookingException;
import com.yorbit.moviebooking.exception.BookingNotFoundException;
import com.yorbit.moviebooking.exception.CinemaNotFoundException;
import com.yorbit.moviebooking.exception.DbException;
import com.yorbit.moviebooking.exception.MovieNotFoundException;
import com.yorbit.moviebooking.exception.ScreenNotFoundException;
import com.yorbit.moviebooking.exception.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Value("${globalexception.message}")
	String errorMsg;
	
	private static final Logger LOGGER = Logger.getLogger(RestExceptionHandler.class);
	
    @ExceptionHandler({ BookingException.class })
    protected ResponseEntity<Object> handleBookingException(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    @ExceptionHandler({ BookingNotFoundException.class })
    protected ResponseEntity<Object> handleBookingNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler({ CinemaNotFoundException.class })
    protected ResponseEntity<Object> handleCinemaNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler({ MovieNotFoundException.class })
    protected ResponseEntity<Object> handleMovieNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler({ ScreenNotFoundException.class })
    protected ResponseEntity<Object> handleScreenNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler({ UserNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler({ Exception.class, DbException.class })
    protected ResponseEntity<Object> globalExceptionHandler(
      Exception ex, WebRequest request) {
    	LOGGER.error(ex.getMessage());
        return handleExceptionInternal(ex, errorMsg, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}