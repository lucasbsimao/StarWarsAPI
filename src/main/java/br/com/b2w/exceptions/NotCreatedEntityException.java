package br.com.b2w.exceptions;

public class NotCreatedEntityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NotCreatedEntityException(String message) {
		super(message);
	}
	
	public NotCreatedEntityException(Exception ex, String message) {
		super(message, ex);
	}
}
