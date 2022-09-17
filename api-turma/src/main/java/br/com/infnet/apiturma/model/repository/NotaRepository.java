package br.com.infnet.apiturma.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiturma.model.domain.Aluno;
import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.domain.Nota;
import br.com.infnet.apiturma.model.domain.PeriodoEnum;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer>{
	
	public List<Nota> findByDisciplinaAndPeriodoAndAno(
			Disciplina disciplina, PeriodoEnum periodo, Integer ano);
	
	public List<Nota> findByDisciplinaAndAno(
			Disciplina disciplina, Integer ano);
	
	public Nota findByAlunoAndDisciplinaAndPeriodoAndAno(Aluno aluno, 
			Disciplina disciplina, PeriodoEnum periodo, Integer ano);

}
