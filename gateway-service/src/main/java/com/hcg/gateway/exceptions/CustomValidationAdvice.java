package com.hcg.gateway.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcg.gateway.utils.Utils;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Slf4j
@RestControllerAdvice
public class CustomValidationAdvice {

	@ExceptionHandler(NumberFormatException.class)
	public final ResponseEntity<CustomExceptionDTO> handleMethodArgumentNotValidException(NumberFormatException exception) {

		final CustomExceptionDTO validationErrorResponse = new CustomExceptionDTO();
		validationErrorResponse.setTime(LocalDateTime.now());
		validationErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
		validationErrorResponse.setMessage("Invalid path variable or request body");

		return ResponseEntity.status(validationErrorResponse.getStatus()).body(validationErrorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<CustomExceptionDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

		final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		final CustomExceptionDTO validationErrorResponse = new CustomExceptionDTO();
		validationErrorResponse.setTime(LocalDateTime.now());
		validationErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
		validationErrorResponse.setInputErrors(getInputErrorDTO(fieldErrors));

		return ResponseEntity.status(validationErrorResponse.getStatus()).body(validationErrorResponse);
	}

	private List<InputErrorDTO> getInputErrorDTO(List<FieldError> errors) {
		List<InputErrorDTO> errorDTOS = new ArrayList<>();
		errors.forEach(err -> {
			InputErrorDTO errorDTO = new InputErrorDTO();
			errorDTO.setField(err.getField());
			errorDTO.setMessage(err.getDefaultMessage());
			errorDTOS.add(errorDTO);
		});
		return errorDTOS;
	}

}
