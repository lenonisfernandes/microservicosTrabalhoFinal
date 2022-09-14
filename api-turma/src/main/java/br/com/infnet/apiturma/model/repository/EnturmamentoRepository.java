package br.com.infnet.apiturma.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiturma.model.domain.Aluno;
import br.com.infnet.apiturma.model.domain.Enturmamento;
import br.com.infnet.apiturma.model.domain.Turma;

@Repository
public interface EnturmamentoRepository 
	extends JpaRepository<Enturmamento, Integer>{
	
	public List<Enturmamento> findByAluno(Aluno aluno);
	
	public List<Enturmamento> findByTurmaAndAtivo(Turma turma, Boolean ativo);
	

}
