package br.com.fagner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResponseExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<FinancialSchedulingErrors> handleValidacaoException(BusinessException ex) {
		return new ResponseEntity<>(new FinancialSchedulingErrors(ex), HttpStatus.BAD_REQUEST);
	}
}
