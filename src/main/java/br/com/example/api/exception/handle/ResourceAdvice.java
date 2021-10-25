package br.com.example.api.exception.handle;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.example.api.exception.NotFoundException;

@RestControllerAdvice
public class ResourceAdvice {

	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Message handleNotFoundException(NotFoundException ex, WebRequest request) {
		return Message.builder().cod(HttpStatus.NOT_FOUND.value()).date(Instant.now()).message(ex.getMessage()).build();
	}
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Message handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		var errors = new StringBuilder();
		 ex.getBindingResult().getAllErrors().forEach((error) -> {
		        errors.append(String.format("%s %n", error.getDefaultMessage()));
		 });
		return Message.builder().cod(HttpStatus.BAD_REQUEST.value()).date(Instant.now()).message(errors.toString()).build();
	}
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Message handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		return Message.builder().cod(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.date(Instant.now())
				.message("Field is being duplicated or already exists").build();
	}
}
