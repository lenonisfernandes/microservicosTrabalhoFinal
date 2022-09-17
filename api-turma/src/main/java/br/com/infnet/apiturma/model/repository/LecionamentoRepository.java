package br.com.infnet.apiturma.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.domain.Lecionamento;
import br.com.infnet.apiturma.model.domain.Professor;
import br.com.infnet.apiturma.model.domain.Turma;

@Repository
public interface LecionamentoRepository extends JpaRepository<Lecionamento, Integer>{
	
	public Lecionamento findByAtivoAndDisciplinaAndTurma(
			Boolean ativo, Disciplina disciplina, Turma turma);
	
	public List<Lecionamento> findByAtivoAndTurma(Boolean ativo, Turma turma);
	
	public List<Lecionamento> findByAtivoAndProfessor(Boolean ativo, 
			Professor professor);
	
	public List<Lecionamento> findByAtivoAndProfessorAndTurma(Boolean ativo,
			Professor professor, Turma turma);

}
