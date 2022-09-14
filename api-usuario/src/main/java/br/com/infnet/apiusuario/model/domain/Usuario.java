package br.com.infnet.apiusuario.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
		use=JsonTypeInfo.Id.NAME, 
		include=JsonTypeInfo.As.PROPERTY, 
		property="tipo")
@JsonSubTypes({
	@JsonSubTypes.Type(value= Aluno.class, name = "Aluno"), 
	@JsonSubTypes.Type(value= Professor.class, name = "Professor"), 
	@JsonSubTypes.Type(value= Coordenador.class, name = "Coordenador")})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String senha;
	private String nome;
	
	public void clone(Usuario usuario) {
		this.login = usuario.login;
		this.senha = usuario.senha;
		this.nome = usuario.nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
