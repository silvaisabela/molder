package br.com.sistema.molde.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.molde.controller.dto.ContaDto;
import br.com.sistema.molde.model.Conta;
import br.com.sistema.molde.repository.ContaRepository;

@RestController
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;

	@RequestMapping("/contas")
	public List<ContaDto> lista() {

		List<Conta> contas = contaRepository.findAll();
		
		return ContaDto.converter(contas);

	}
}