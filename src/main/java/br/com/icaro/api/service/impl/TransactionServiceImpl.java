package br.com.icaro.api.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icaro.api.domain.Transaction;
import br.com.icaro.api.domain.exception.AccountNotFoundException;
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
			throws AccountNotFoundException, OperationTypeNotFoundException {
		var account = accountService.getAccountById(accountId);
		var operationType = operationTypeService.getOperationTypeById(operationTypeId);
		
		var transaction = Transaction.builder()
				.account(account)
				.operationType(operationType)
				.amount(amount)
				.eventDate(LocalDateTime.now())
				.build();
		
		return transactionRepository.save(transaction);
	}

}
