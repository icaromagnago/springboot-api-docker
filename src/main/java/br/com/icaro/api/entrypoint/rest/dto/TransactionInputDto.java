package br.com.icaro.api.entrypoint.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "TransactionInputDto", description = "Dto for create a new transaction")
@Data
public class TransactionInputDto {
	
	@ApiModelProperty(position = 0, required = true, value = "Account's id", example = "23")
	@NotNull(message = "account_id must not be null")
	@JsonProperty("account_id")
	private Integer accountId;
	
	@ApiModelProperty(position = 1, required = true, 
			value = "Operation's type id", example = "1", 
			allowableValues = "1, 2, 3, 4")
	@NotNull(message = "operation_type_id must not be null")
	@Min(value = 1, message = "operation_type_id must be between {min} and {max}")
	@Max(value = 4, message = "operation_type_id must be between {min} and {max}")
	@JsonProperty("operation_type_id")
	private Integer operationTypeId;
	
	@ApiModelProperty(position = 2, required = true, 
			value = "Transaction's amount. Must be a positive number", example = "100.50")
	@Positive(message = "amount must be a positive number")
	private double amount;

}
