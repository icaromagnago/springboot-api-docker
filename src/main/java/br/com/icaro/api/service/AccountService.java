package br.com.icaro.api.service;

import br.com.icaro.api.domain.Account;
import br.com.icaro.api.domain.exception.AccountNotFoundException;

public interface AccountService {
	
	Account createAccount(String documentNumber);
	
	Account updateAccount(Account account);
	
	Account getAccountById(Integer accountId) throws AccountNotFoundException;
}
