package br.com.icaro.api.service;

import br.com.icaro.api.domain.OperationType;
import br.com.icaro.api.domain.exception.OperationTypeNotFoundException;

public interface OperationTypeService {
	OperationType getOperationTypeById(Integer operationTypeId) throws OperationTypeNotFoundException;
}
