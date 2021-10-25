package br.com.example.api.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1900301061836044610L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
