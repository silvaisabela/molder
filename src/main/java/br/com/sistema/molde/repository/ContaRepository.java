package br.com.sistema.molde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.molde.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{
	Conta findByEmail(String email);
	
	@Query(value = "SELECT max(id_conta) from Conta")
	int findGreatestId();
}
