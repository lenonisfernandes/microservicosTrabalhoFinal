package br.com.infnet.appnotas.model.domain;

import java.util.List;

enum Ensino {
	INFANTIL,
	FUNDAMENTAL_I,
	FUNDAMENTAL_II,
	MEDIO
}

public class Serie {
	
	private Integer id;
	private Ensino ensino;
	private Integer numero;
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
