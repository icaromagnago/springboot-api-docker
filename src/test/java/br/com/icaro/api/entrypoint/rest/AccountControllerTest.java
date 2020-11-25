package br.com.icaro.api.entrypoint.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.icaro.api.domain.Account;
import br.com.icaro.api.service.AccountService;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountService accountService;
	
	@Test
	void whenPostValidAccount_thenReturnsCreated201() throws Exception {
		var accountDto = "{"
				+ "\"document_number\": \"11998876543\""
				+ "}";
		
		var account = new Account(1, "11998876543");
		when(accountService.createAccount(account.getDocumentNumber())).thenReturn(account);
		
		mockMvc
			.perform(post("/accounts")
					.contentType(MediaType.APPLICATION_JSON)
					.content(accountDto))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.account_id", is(account.getId())))
			.andExpect(jsonPath("$.document_number", is(account.getDocumentNumber())));
	}
	
	@Test
	void whenPostInvalidAccountNumber_thenReturnsBadResquest400() throws Exception {
		var accountInvalidNumber = "{"
				+ "\"document_number\": \"1199887654322\""
				+ "}";
		
		mockMvc
			.perform(post("/accounts")
					.contentType(MediaType.APPLICATION_JSON)
					.content(accountInvalidNumber))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$[0]", is("documentNumber: document_number must have a maximum of 11 numbers")));
	}
	
	@Test
	void whenGetAccount_thenReturnsOk200() throws Exception {		
		var account = new Account(1, "11998876543");
		when(accountService.getAccountById(1)).thenReturn(account);
		
		mockMvc
		.perform(get("/accounts/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.account_id", is(account.getId())))
		.andExpect(jsonPath("$.document_number", is(account.getDocumentNumber())));
	}
}
