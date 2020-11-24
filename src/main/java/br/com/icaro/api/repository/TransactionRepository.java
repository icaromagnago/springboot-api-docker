package br.com.icaro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.icaro.api.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
