package br.com.icaro.api.service;

import br.com.icaro.api.domain.Transaction;
import br.com.icaro.api.domain.exception.AccountNotFoundException;
import br.com.icaro.api.domain.exception.NoLimitException;
import br.com.icaro.api.domain.exception.OperationTypeNotFoundException;

public interface TransactionService {
	Transaction newTransaction(Integer accountId, Integer operationTypeId, Double amount) 
			throws AccountNotFoundException, OperationTypeNotFoundException, NoLimitException;
}
