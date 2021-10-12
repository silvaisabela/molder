package br.com.sistema.molde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.molde.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer>{
	ConfirmationToken findByToken(String token);
	
	@Query(value = "SELECT nvl(max(id), 0) from ConfirmationToken")
	int findGreatestId();
}
