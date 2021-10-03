package br.com.sistema.molde.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CONTA")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_conta;

	private String email;
	private String senha;
	private char email_verificacao;
	
	public int getId() {
		return id_conta;
	}
	public void setId(int id_conta) {
		this.id_conta = id_conta;
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
	public char getEmail_verificacao() {
		return email_verificacao;
	}
	public void setEmail_verificacao(char email_verificacao) {
		this.email_verificacao = email_verificacao;
	}
	
	

}
