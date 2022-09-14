package br.com.infnet.apiturma.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

enum Ensino {
	INFANTIL,
	FUNDAMENTAL_I,
	FUNDAMENTAL_II,
	MEDIO
}

@Entity
public class Serie {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Ensino ensino;
	private Integer numero;
	@OneToMany(
			mappedBy = "serie", 
			fetch = FetchType.LAZY, 
			orphanRemoval = true)
	@JsonManagedReference
	private List<Turma> turmas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ensino getEnsino() {
		return ensino;
	}
	public void setEnsino(Ensino ensino) {
		this.ensino = ensino;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public List<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}
