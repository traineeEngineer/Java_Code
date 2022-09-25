package com.Validations.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Validations.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String , String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach((error)->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleBusinessException(UserNotFoundException e){
		Map<String, String> errorMap=new HashMap<>();
		errorMap.put("error message", e.getMessage());
		return errorMap;
	}
}
