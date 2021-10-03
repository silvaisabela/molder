package br.com.sistema.molde.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.molde.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
