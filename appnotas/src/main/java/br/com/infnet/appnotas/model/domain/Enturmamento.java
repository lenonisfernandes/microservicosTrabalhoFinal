package br.com.infnet.appnotas.model.domain;

import java.time.LocalDate;

public class Enturmamento {
	
	private Integer id;
	private Turma turma;
	private Aluno aluno;
	private LocalDate entrada;
	private Boolean ativo;
	
	public void clone(Enturmamento enturmamento) {
		this.turma = enturmamento.getTurma();
		this.aluno = enturmamento.getAluno();
		this.entrada = enturmamento.getEntrada();
		this.ativo = enturmamento.getAtivo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDate getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
	

}
