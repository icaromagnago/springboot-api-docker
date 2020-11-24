package br.com.icaro.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icaro.api.domain.OperationType;
import br.com.icaro.api.domain.exception.OperationTypeNotFoundException;
import br.com.icaro.api.repository.OperationTypeRepository;
import br.com.icaro.api.service.OperationTypeService;

@Service
public class OperationTypeServiceImpl implements OperationTypeService {

	@Autowired
	private OperationTypeRepository operationTypeRepository;
	
	@Override
	public OperationType getOperationTypeById(Integer operationTypeId) throws OperationTypeNotFoundException {
		
		return operationTypeRepository.findById(operationTypeId)
				.orElseThrow(() -> new OperationTypeNotFoundException(
						String.format("Operation type with id %s not found", operationTypeId)));
	}

}
