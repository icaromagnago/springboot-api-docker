package br.com.icaro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.icaro.api.domain.OperationType;

public interface OperationTypeRepository extends JpaRepository<OperationType, Integer> {

}
