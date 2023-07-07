package com.hcg.gateway.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcg.gateway.utils.Utils;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Slf4j
@RestControllerAdvice
public class InputValidationAdvice {


	@ExceptionHandler(StatusRuntimeException.class)
	public final ResponseEntity<CustomExceptionDTO> handleMethodArgumentNotValidException(StatusRuntimeException exception) throws JsonProcessingException {
		CustomExceptionDTO dto = new CustomExceptionDTO();
		dto.setTime(LocalDateTime.now());
		dto.setMessage(exception.getMessage());
		Status.Code code = exception.getStatus().getCode();

		switch (code) {
			case UNAUTHENTICATED:
				dto.setStatus(HttpStatus.UNAUTHORIZED);
				break;
			case INVALID_ARGUMENT:
				dto.setStatus(HttpStatus.BAD_REQUEST);
				List<InputErrorDTO> errors = getInputErrorDTO(exception);
				if (errors == null) {
					dto.setMessage(exception.getMessage());
				}
				break;
			case ALREADY_EXISTS:
				dto.setStatus(HttpStatus.BAD_REQUEST);
				break;
			case NOT_FOUND:
				dto.setStatus(HttpStatus.NOT_FOUND);
				break;
			default:
				dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				break;
		}

		return ResponseEntity.status(dto.getStatus()).body(dto);
	}


	private List<InputErrorDTO> getInputErrorDTO(StatusRuntimeException ex) throws JsonProcessingException {
		Metadata metadata = ex.getTrailers();
		String errorStr = Optional.ofNullable(metadata)
				.map(meta ->  meta.get(InputErrorConst.KEY))
				.orElse(null);
		if (errorStr != null ){
			return Utils.getListFromJson(errorStr);
		}
		return null;
	}

}
