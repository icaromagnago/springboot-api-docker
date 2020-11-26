package br.com.icaro.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icaro.api.domain.Transaction;
import br.com.icaro.api.domain.exception.AccountNotFoundException;
import br.com.icaro.api.domain.exception.NoLimitException;
import br.com.icaro.api.domain.exception.OperationTypeNotFoundException;
import br.com.icaro.api.repository.TransactionRepository;
import br.com.icaro.api.service.AccountService;
import br.com.icaro.api.service.OperationTypeService;
import br.com.icaro.api.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired
	private OperationTypeService operationTypeService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction newTransaction(Integer accountId, Integer operationTypeId, Double amount) 
			throws AccountNotFoundException, OperationTypeNotFoundException, NoLimitException {
		var account = accountService.getAccountById(accountId);
		var operationType = operationTypeService.getOperationTypeById(operationTypeId);
		
		var transaction = new Transaction(amount, account, operationType);
		
		accountService.updateAccount(account);
		
		return transactionRepository.save(transaction);
	}

}
