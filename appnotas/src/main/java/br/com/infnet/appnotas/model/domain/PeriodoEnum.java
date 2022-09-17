package br.com.infnet.appnotas.model.domain;

public enum PeriodoEnum {
	
	BIM_1("1º Bimestre"),
	BIM_2("2º Bimestre"),
	BIM_3("3º Bimestre"),
	BIM_4("4º Bimestre"),
	REC_1("1º Recuperação"),
	REC_2("2º Recuperação");
	
	String name;
	
	PeriodoEnum(String name) {
		this.name = name;
	}
	
	String getName() {
		return this.name;
	}

}
