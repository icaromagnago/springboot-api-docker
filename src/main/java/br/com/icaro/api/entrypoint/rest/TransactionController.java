package br.com.icaro.api.entrypoint.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.icaro.api.domain.exception.AccountNotFoundException;
import br.com.icaro.api.domain.exception.NoLimitException;
import br.com.icaro.api.domain.exception.OperationTypeNotFoundException;
import br.com.icaro.api.entrypoint.rest.dto.CreatedTransactionDto;
import br.com.icaro.api.entrypoint.rest.dto.TransactionInputDto;
import br.com.icaro.api.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api("Api for transactions")
@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@ApiOperation("Creates a new transaction")
	@ApiResponse(code = 201, response = CreatedTransactionDto.class, message = "")
	@PostMapping
	public ResponseEntity<CreatedTransactionDto> createTransaction(@Valid @RequestBody TransactionInputDto transactionInput) 
			throws AccountNotFoundException, OperationTypeNotFoundException, NoLimitException {
		
		var transaction = transactionService.newTransaction(transactionInput.getAccountId(),
				transactionInput.getOperationTypeId(), 
				transactionInput.getAmount());
		
		var transactionDto = CreatedTransactionDto.builder()
				.id(transaction.getId())
				.accountId(transaction.getAccount().getId())
				.operationTypeId(transaction.getOperationType().getId())
				.amount(transaction.getAmount())
				.eventDate(transaction.getEventDate())
				.build();
		
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(transaction.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(transactionDto);
	}
}
