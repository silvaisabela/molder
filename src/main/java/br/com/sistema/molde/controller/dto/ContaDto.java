package br.com.sistema.molde.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sistema.molde.model.Conta;

public class ContaDto {
	
	private int id_conta;
	private String email;
	private String senha;
	private boolean email_verificacao;
	
	public ContaDto(Conta conta) {
		this.id_conta = conta.getId();
		this.email = conta.getEmail();
		this.senha = conta.getSenha();
		this.email_verificacao = conta.getEmail_verificacao();
	}
	
	public int getId() {
		return id_conta;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public boolean getEmail_verificacao() {
		return email_verificacao;
	}

	public static List<ContaDto> converter(List<Conta> contas) {
		return contas.stream().map(ContaDto::new).collect(Collectors.toList());
	}
	
	
}
