package br.com.icaro.api.entrypoint.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.icaro.api.domain.exception.AccountNotFoundException;
import br.com.icaro.api.entrypoint.rest.dto.AccountInputDto;
import br.com.icaro.api.entrypoint.rest.dto.CreatedAccountDto;
import br.com.icaro.api.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Api for accounts")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@ApiOperation("Creates a new account")
	@ApiResponse(code = 201, response = CreatedAccountDto.class, message = "")
	@PostMapping
	public ResponseEntity<CreatedAccountDto> createAccount(@Valid @RequestBody AccountInputDto accountInput) {
		var account = accountService.createAccount(accountInput.getDocumentNumber());
		
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(account.getId())
				.toUri();
		
		var accountDto = new CreatedAccountDto(account.getId(), 
				account.getDocumentNumber(), 
				account.getAvailableCreditLimit());
		return ResponseEntity.created(uri).body(accountDto);
	}
	
	@ApiOperation("Gets a account by id")
	@ApiResponses({
		@ApiResponse(code = 200, response = CreatedAccountDto.class, message = "Ok"),
		@ApiResponse(code = 404, response = String.class, message = "Not found")
	})
	@GetMapping("/{accountId}")
	public ResponseEntity<CreatedAccountDto> getAccount(@PathVariable Integer accountId) throws AccountNotFoundException {
		var account = accountService.getAccountById(accountId);
		var accountDto = new CreatedAccountDto(account.getId(), 
				account.getDocumentNumber(), 
				account.getAvailableCreditLimit());
		
		return ResponseEntity.ok(accountDto);
	}

}
