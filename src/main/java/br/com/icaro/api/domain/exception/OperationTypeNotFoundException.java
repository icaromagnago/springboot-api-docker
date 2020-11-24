package br.com.icaro.api.domain.exception;

public class OperationTypeNotFoundException extends Exception {

	static final long serialVersionUID = 479439466789356356L;

	public OperationTypeNotFoundException(String message) {
		super(message);
	}
}
