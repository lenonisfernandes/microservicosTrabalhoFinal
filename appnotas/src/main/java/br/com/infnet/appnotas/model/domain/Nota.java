package br.com.infnet.appnotas.model.domain;

public class Nota {

	private Integer id;
	private Aluno aluno;
	private Disciplina disciplina;
	private Double valor;
	private Integer ano;
	private PeriodoEnum periodo;
	
	@Override
	public String toString() {
		if(this.getValor().toString()==null) {
			return "-";
		}
		else {
			return this.getValor().toString();
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public PeriodoEnum getPeriodo() {
		return periodo;
	}
	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}
	
	

}
