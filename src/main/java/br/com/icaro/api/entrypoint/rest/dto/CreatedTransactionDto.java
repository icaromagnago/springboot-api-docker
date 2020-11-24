package br.com.icaro.api.entrypoint.rest.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatedTransactionDto {
	
	private Integer id;
	
	private double amount;
	
	@JsonProperty("event_date")
	private LocalDateTime eventDate;
	
	@JsonProperty("account_id")
	private Integer accountId;
	
	@JsonProperty("operation_type_id")
	private Integer operationTypeId;
}
