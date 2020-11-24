package br.com.icaro.api.domain.exception;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 8595332639991371913L;
	
	public AccountNotFoundException(String message) {
		super(message);
	}

}
