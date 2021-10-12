package br.com.sistema.molde.service;

import org.springframework.stereotype.Service;

import br.com.sistema.molde.model.ConfirmationToken;
import br.com.sistema.molde.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}

	public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
		super();
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
	
}
