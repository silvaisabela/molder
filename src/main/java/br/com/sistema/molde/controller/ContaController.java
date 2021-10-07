package br.com.sistema.molde.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistema.molde.controller.dto.ContaDto;
import br.com.sistema.molde.controller.form.ContaForm;
import br.com.sistema.molde.model.Conta;
import br.com.sistema.molde.repository.ContaRepository;

@RestController
@RequestMapping(value= "/auth/accounts")
public class ContaController {
	int id_atual = 1;
	
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping
	public List<ContaDto> lista() {

		List<Conta> contas = contaRepository.findAll();
		
		return ContaDto.converter(contas);

	}
	
	@PostMapping
	public ResponseEntity<ContaDto> createAccount(@RequestBody ContaForm form, UriComponentsBuilder uriBuilder) throws Exception {
		Conta conta = form.converter(id_atual++);
		if(contaRepository.findByEmail(conta.getEmail()) == null) {
			conta.setSenha(encoder.encode(conta.getSenha()));
			System.out.println("encoder: "+ encoder);
			System.out.println("senha: "+ encoder.encode(conta.getSenha()));
			contaRepository.save(conta);
		}else {
			throw new Exception("Email "+ conta.getEmail()+ " já cadastrado");
		}

		URI uri = uriBuilder.path("/auth/accounts/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}
}