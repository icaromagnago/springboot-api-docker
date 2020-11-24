package br.com.icaro.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icaro.api.domain.Account;
import br.com.icaro.api.domain.exception.AccountNotFoundException;
import br.com.icaro.api.repository.AccountRepository;
import br.com.icaro.api.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository; 
	
	@Override
	public Account createAccount(String documentNumber) {
		var newAccount = new Account(documentNumber);
		
		return accountRepository.save(newAccount);
	}

	@Override
	public Account getAccountById(Integer accountId) throws AccountNotFoundException {
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException(
						String.format("Account with id %s not found", accountId)));
	}

}
