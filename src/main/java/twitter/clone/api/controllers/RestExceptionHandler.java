package twitter.clone.api.controllers;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.SignatureException;
import twitter.clone.api.dto.ErrorDto;

@ControllerAdvice
public class RestExceptionHandler {
    //add exception handler



	@ExceptionHandler
	public ResponseEntity<ErrorDto> handleException(Exception exc) {

		System.out.println("Rest Exception Handler");
		ErrorDto errorResponse =  new ErrorDto();
		errorResponse.setError(exc.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}



}
