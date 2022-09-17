package br.com.infnet.apiturma.model.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lecionamento {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="idProfessor")
	private Professor professor;
	@ManyToOne
	@JoinColumn(name="idTurma")
	private Turma turma;
	@ManyToOne
	@JoinColumn(name="idDisciplina")
	private Disciplina disciplina;
	private Boolean ativo;
	private LocalDate entrada;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public LocalDate getEntrada() {
		return entrada;
	}
	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
	}
	
	
	

}
