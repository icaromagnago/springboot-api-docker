package br.com.icaro.api.entrypoint.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.icaro.api.domain.Account;
import br.com.icaro.api.domain.OperationType;
import br.com.icaro.api.domain.Transaction;
import br.com.icaro.api.service.TransactionService;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TransactionService transactionService;
	
	@Test
	void whenPostValidTransaction_thenReturnsCreated201() throws Exception {
		var transactionDto = "{"
				+ "\"account_id\": \"1\", "
				+ "\"operation_type_id\": \"4\", "
				+ "\"amount\": \"100.0\""
				+ "}";
		
		var account = new Account(1, "123456789123");
		var operationType = new OperationType(4, "");
		var transaction = new Transaction(100.0d, account, operationType);
		transaction.setId(1);
		
		when(transactionService.newTransaction(1, 4, 100.0d)).thenReturn(transaction);
		
		mockMvc
			.perform(post("/transactions")
					.contentType(MediaType.APPLICATION_JSON)
					.content(transactionDto))
			.andExpect(status().isCreated())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(transaction.getId())))
			.andExpect(jsonPath("$.amount", is(transaction.getAmount())))
			.andExpect(jsonPath("$.account_id", is(account.getId())))
			.andExpect(jsonPath("$.operation_type_id", is(operationType.getId())));
	}
	
	@Test
	void whenPostNullAccountId_thenReturnsBadResquest400() throws Exception {
		var transactionMissingAccountId = "{"
				+ "\"operation_type_id\": \"1\", "
				+ "\"amount\": \"100.5\""
				+ "}";
		
		mockMvc
			.perform(post("/transactions")
					.contentType(MediaType.APPLICATION_JSON)
					.content(transactionMissingAccountId))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$[0]", is("accountId: account_id must not be null")));
	}
	
	@Test
	void whenPostNegativeAmount_thenReturnsBadResquest400() throws Exception {
		var transactionNegativeAmount = "{"
				+ "\"account_id\": \"1\", "
				+ "\"operation_type_id\": \"1\", "
				+ "\"amount\": \"-100.5\""
				+ "}";
		
		mockMvc
			.perform(post("/transactions")
					.contentType(MediaType.APPLICATION_JSON)
					.content(transactionNegativeAmount))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$[0]", is("amount: amount must be a positive number")));
	}

}
