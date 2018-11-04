package br.com.b2w.exceptions;

public class NotFoundEntityException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NotFoundEntityException(String message) {
		super(message);
	}
	
	public NotFoundEntityException(Exception ex, String message) {
		super(message, ex);
	}
}
