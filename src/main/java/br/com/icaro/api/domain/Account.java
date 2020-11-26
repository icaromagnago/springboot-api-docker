package br.com.icaro.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.icaro.api.domain.exception.NoLimitException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer id;
	
	@Column(name = "document_number")
	private String documentNumber;
	
	@Column(name = "available_credit_limit")
	private double availableCreditLimit;
	
	public Account(String documentNumber) {
		this.documentNumber = documentNumber;
		this.availableCreditLimit = 0.0;
	}
	
	public void addLimit(double amount) {
		this.availableCreditLimit += amount;
	}
	
	public void debit(double amount) throws NoLimitException {
		if (hasLimit(amount)) {
			this.availableCreditLimit -= amount;
		} else {
			throw new NoLimitException("No enough limit.");
		}
	}

	private boolean hasLimit(Double amount) {
		return this.availableCreditLimit >= amount;
	}
}
