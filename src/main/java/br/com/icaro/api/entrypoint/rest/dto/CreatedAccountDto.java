package br.com.icaro.api.entrypoint.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class CreatedAccountDto {
	
	@JsonProperty("account_id")
	private int accountId;
	
	@JsonProperty("document_number")
	private String documentNumber;
}
