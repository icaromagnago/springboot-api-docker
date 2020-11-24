package br.com.icaro.api.entrypoint.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountInputDto {
	
	@NotEmpty(message = "document_number must not be null nor empty")
	@Size(max = 11, message = "document_number must have a maximum of {max} numbers")
	@JsonProperty("document_number")
	private String documentNumber;
}
