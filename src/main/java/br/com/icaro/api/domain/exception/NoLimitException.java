package br.com.icaro.api.domain.exception;

public class NoLimitException extends Exception {

	private static final long serialVersionUID = 8595332639991371913L;
	
	public NoLimitException(String message) {
		super(message);
	}

}
