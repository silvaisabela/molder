package br.com.sistema.molde.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistema.molde.controller.dto.ContaDto;
import br.com.sistema.molde.controller.form.ContaForm;
import br.com.sistema.molde.model.ConfirmationToken;
import br.com.sistema.molde.model.Conta;
import br.com.sistema.molde.repository.ConfirmationTokenRepository;
import br.com.sistema.molde.repository.ContaRepository;
import br.com.sistema.molde.service.ConfirmationTokenService;
import br.com.sistema.molde.service.EmailSenderService;

@RestController
@RequestMapping(value = "/auth")
public class ContaController {

	int id_atual = 1;
	int id_atual_token = 1;

	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private EmailSenderService senderEmail;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@GetMapping
	public List<ContaDto> lista() {

		List<Conta> contas = contaRepository.findAll();

		return ContaDto.converter(contas);

	}

	@PostMapping(value="/accounts")
	public ResponseEntity createAccount(@RequestBody ContaForm form, UriComponentsBuilder uriBuilder) throws Exception {
		id_atual = contaRepository.findGreatestId() + 1 ;
		id_atual_token = confirmationTokenRepository.findGreatestId() + 1 ;
		Conta conta = form.converter(id_atual);
		try {
			conta.setSenha(encoder.encode(conta.getSenha()));
			System.out.println("encoder: "+ encoder);
			System.out.println("senha: "+ encoder.encode(conta.getSenha()));
			contaRepository.save(conta);
			String token = UUID.randomUUID().toString();
			try {
				ConfirmationToken confirmationToken = new ConfirmationToken(id_atual, token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), conta);
				confirmationTokenService.saveConfirmationToken(confirmationToken);
			}catch(Exception e) {
				return new ResponseEntity<>(e, HttpStatus.CONFLICT);
			}
			
			String message = "Olá! Obrigado por se registrar no Molde. Clique no link para confirmar o seu email: ";
			message+= " http://localhost:8080/auth/email-validate?token="+token;
			
			senderEmail.sendSimpleMessage(conta.getEmail(), "Confirmação de senha", message);
			
		}catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>("Email já cadastrado", HttpStatus.CONFLICT);
		}


		URI uri = uriBuilder.path("/auth/accounts/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}
	
	@GetMapping(path = "email-validate")
	public ResponseEntity confirm(@RequestParam("token") String token) {
		
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
		
		if(confirmationToken.getConfirmedAt() != null) {
			return new ResponseEntity<>("Email já confirmado", HttpStatus.CONFLICT);
		}
		
		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		if(expiredAt.isBefore(LocalDateTime.now())) {
			return new ResponseEntity<>("Tempo de confirmação de email expirado", HttpStatus.CONFLICT);
		}
		
		confirmationToken.setConfirmedAt(LocalDateTime.now());
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		//TODO setVerificacaoEmail TB_CONTA
		Conta conta = contaRepository.findById(confirmationToken.getConta().getId());
		conta.setEmail_verificacao(true);
		contaRepository.save(conta);
		
		return new ResponseEntity<>("Email confirmado", HttpStatus.OK);
	}
	
}






