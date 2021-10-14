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
	private int id_conta;

	private String email;
	private String senha;
	private boolean email_verificacao;
	
	public Conta() {
	}
		
	public Conta(int id_conta, String email, String senha) {
		super();
		this.id_conta = id_conta;
		this.email = email;
		this.senha = senha;
		this.email_verificacao = false;
	}

	
	public int getId_conta() {
		return id_conta;
	}

	public void setId_conta(int id_conta) {
		this.id_conta = id_conta;
	}

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
	public boolean getEmail_verificacao() {
		return email_verificacao;
	}
	public void setEmail_verificacao(boolean email_verificacao) {
		this.email_verificacao = email_verificacao;
	}
	
	

}
