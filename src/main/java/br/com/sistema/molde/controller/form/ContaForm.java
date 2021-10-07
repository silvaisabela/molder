package br.com.sistema.molde.controller.form;

import br.com.sistema.molde.model.Conta;

public class ContaForm {

	private int id;
	private String email;
	private String senha;
	private boolean email_verificacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean getEmail_verificacao() {
		return email_verificacao;
	}
	public void setEmail_verificacao(boolean email_verificacao) {
		this.email_verificacao = email_verificacao;
	}
	public Conta converter(int id_atual) {
		return new Conta(id_atual, email, senha, email_verificacao);
	}
	
	
}
