package br.com.sistema.molde.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TOKEN")
public class ConfirmationToken {
	@Id
	private int id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime created_at;
	@Column(nullable = false)
	private LocalDateTime expires_at;
	private LocalDateTime confirmed_at;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_conta")
	private Conta conta;

	public ConfirmationToken(int id, String token, LocalDateTime created_at, LocalDateTime expires_at, Conta conta) {
		super();
		this.id = id;
		this.token = token;
		this.created_at = created_at;
		this.expires_at = expires_at;
		this.conta = conta;
	}

	public ConfirmationToken() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.created_at = createdAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmed_at;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmed_at = confirmedAt;
	}

	public LocalDateTime getExpiresAt() {
		return expires_at;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expires_at = expiresAt;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
