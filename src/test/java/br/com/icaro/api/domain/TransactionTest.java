package br.com.icaro.api.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.icaro.api.domain.exception.NoLimitException;

class TransactionTest {

	@Test
	void whenNewPaymentTransaction() throws NoLimitException {
		var account = new Account(1, "12345678912", 100.0);
		var transaction = new Transaction(100.0d, account, new OperationType(4, ""));
		
		assertAll(
			() -> assertEquals(100.0d, transaction.getAmount()),
			() -> assertNotNull(transaction.getAccount()),
			() -> assertNotNull(transaction.getEventDate()),
			() -> assertNotNull(transaction.getOperationType()),
			() -> assertEquals(200.0, account.getAvailableCreditLimit()));
	}
	
	@Test
	void whenNewTransactionWithLimit() throws NoLimitException {
		var account = new Account(1, "12345678912", 100.0);
		var transaction = new Transaction(100.0d, account, new OperationType(1, ""));
		
		assertAll(
			() -> assertEquals(-100.0d, transaction.getAmount()),
			() -> assertNotNull(transaction.getAccount()),
			() -> assertNotNull(transaction.getEventDate()),
			() -> assertNotNull(transaction.getOperationType()),
			() -> assertEquals(0.0, account.getAvailableCreditLimit()));
	}
	
	@Test
	void whenNewTransactionWithNoLimit() {
		assertThrows(NoLimitException.class, () -> {
			var account = new Account(1, "12345678912", 100.0);
			new Transaction(200.0d, account, new OperationType(1, ""));
		});
	}

}
