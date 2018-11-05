package br.com.b2w.exceptions.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.b2w.exceptions.NotCreatedEntityException;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.exceptions.response.ErrorDetail;

import org.springframework.validation.ObjectError;

@ControllerAdvice
@RestController
public class StarWarsApiExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail();
		errorDetails.setTimestamp(new Date());
		errorDetails.setError(ex.getMessage());
		errorDetails.setMessage(request.getDescription(false));
		errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setPath(request.getContextPath());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundEntityException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundEntityException ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail();
		errorDetails.setTimestamp(new Date());
		errorDetails.setError("Not found exception");
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetails.setPath(request.getContextPath());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotCreatedEntityException.class)
	public final ResponseEntity<Object> handleNotCreatedEntityException(NotCreatedEntityException ex, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail();
		errorDetails.setTimestamp(new Date());
		errorDetails.setError("Server severity error");
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setPath(request.getContextPath());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDetail> errorDetails = this.createValidatedListResponse(request.getContextPath(),ex.getBindingResult().getAllErrors());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	private List<ErrorDetail> createValidatedListResponse(String path, List<ObjectError> listErrors){
		List<ErrorDetail> errorDetails = new ArrayList<>();
		for(ObjectError error : listErrors) {
			ErrorDetail errorDetail = new ErrorDetail();
			errorDetail.setError("Validation error");
			errorDetail.setTimestamp(new Date());
			errorDetail.setMessage(error.getDefaultMessage());
			errorDetail.setPath(path);
			errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
			errorDetails.add(errorDetail);
		}
		
		return errorDetails;
	}
}
