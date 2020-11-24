package br.com.icaro.api.entrypoint.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionInput {
	
	@NotNull(message = "account_id must not be null")
	@JsonProperty("account_id")
	private Integer accountId;
	
	@NotNull(message = "operation_type_id must not be null")
	@Min(value = 1, message = "operation_type_id must be between {min} and {max}")
	@Max(value = 4, message = "operation_type_id must be between {min} and {max}")
	@JsonProperty("operation_type_id")
	private Integer operationTypeId;
	
	private double amount;

}
