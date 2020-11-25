package br.com.icaro.api.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer id;
	
	private double amount;
	
	@Column(name = "event_date")
	private LocalDateTime eventDate;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "operation_type_id")
	private OperationType operationType;
	
	public Transaction(Double amount, Account account, OperationType operationType) {
		this.account = account;
		this.operationType = operationType;
		this.eventDate = LocalDateTime.now();
		this.amount = operationType.isPayment() ? amount : amount * -1.0d;
	}
}
