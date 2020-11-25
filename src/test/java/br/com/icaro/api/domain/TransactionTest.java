package br.com.icaro.api.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransactionTest {

	@Test
	void testNewPositiveTransaction() {
		var transaction = new Transaction(100.0d, new Account(), new OperationType(4, ""));
		
		assertAll(
			() -> assertEquals(100.0d, transaction.getAmount()),
			() -> assertNotNull(transaction.getAccount()),
			() -> assertNotNull(transaction.getEventDate()),
			() -> assertNotNull(transaction.getOperationType()));
	}
	
	@Test
	void testNewNegativeTransaction() {
		var transaction = new Transaction(100.0d, new Account(), new OperationType(1, ""));
		
		assertAll(
			() -> assertEquals(-100.0d, transaction.getAmount()),
			() -> assertNotNull(transaction.getAccount()),
			() -> assertNotNull(transaction.getEventDate()),
			() -> assertNotNull(transaction.getOperationType()));
	}

}
