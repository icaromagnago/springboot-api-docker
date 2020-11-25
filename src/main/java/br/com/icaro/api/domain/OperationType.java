package br.com.icaro.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operations_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationType {
	
	private static final int PAYMENT_ID = 4;

	@Id
	@Column(name = "operation_type_id")
	private Integer id;
	
	private String description;
	
	public boolean isPayment() {
		return this.id == PAYMENT_ID;
	}
}
