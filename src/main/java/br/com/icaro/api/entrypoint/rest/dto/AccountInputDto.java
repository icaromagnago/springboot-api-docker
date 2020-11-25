package br.com.icaro.api.entrypoint.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AccountInputDto", description = "Dto for create a new account")
@Data
public class AccountInputDto {
	
	@ApiModelProperty(position = 0, required = true, value = "Account document number", example = "12345678965")
	@NotEmpty(message = "document_number must not be null nor empty")
	@Size(max = 11, message = "document_number must have a maximum of {max} numbers")
	@JsonProperty("document_number")
	private String documentNumber;
}
