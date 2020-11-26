package br.com.icaro.api.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.icaro.api.domain.exception.NoLimitException;

class AccountTest {

	@Test
	void whenAddLimit_thenOk() {
		var account = new Account(1, "12345678912", 0.0);
		
		account.addLimit(100.0);
		
		assertEquals(100.0, account.getAvailableCreditLimit());
	}

	@Test
	void whenDebitWithLimit() throws NoLimitException {
		var account = new Account(1, "12345678912", 100.0);
		
		account.debit(50.0);
		
		assertEquals(50.0, account.getAvailableCreditLimit());
	}
	
	@Test
	void whenDebitWithNoLimitAvailable() {
		assertThrows(NoLimitException.class, () -> {
			var account = new Account(1, "12345678912", 100.0);
			
			account.debit(200.0);
		});
	}
}
