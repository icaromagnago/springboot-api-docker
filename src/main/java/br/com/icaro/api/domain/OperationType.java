package br.com.icaro.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operations_types")
@Data
@NoArgsConstructor
public class OperationType {
	
	@Id
	@Column(name = "operation_type_id")
	private Integer id;
	
	private String description;
}
