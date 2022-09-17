package br.com.infnet.appnotas.model.domain;

import java.time.LocalDate;

public class Lecionamento {
	
	private Integer id;
	private Professor professor;
	private Turma turma;
	private Disciplina disciplina;
	private Boolean ativo;
	private LocalDate entrada;
	
	@Override
	public String toString() {
		String prefix = "";
		if (!this.ativo) {
			prefix = "Deixou a turma: ";
		}
		return prefix + this.turma + " - " + this.disciplina;
	}
	
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
