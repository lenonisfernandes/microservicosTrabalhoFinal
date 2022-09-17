package br.com.infnet.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Aluno extends PanacheEntity{
	public String nome;
	public String login;
	public String senha;
	
	public void copiar(Aluno novo) {
		this.nome = novo.nome;
		this.login = novo.login;
		this.senha = novo.senha;
		this.id = novo.id;
	}

}
