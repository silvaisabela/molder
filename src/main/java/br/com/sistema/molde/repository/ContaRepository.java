package br.com.sistema.molde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.molde.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{
	Conta findByEmail(String email);
	Conta findById(int id);
	
	@Query(value = "SELECT nvl(max(id_conta), 0) from Conta")
	int findGreatestId();
}
