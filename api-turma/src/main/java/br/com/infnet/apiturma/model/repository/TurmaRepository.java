package br.com.infnet.apiturma.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiturma.model.domain.Serie;
import br.com.infnet.apiturma.model.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{
	
	public List<Turma> findAllByAno(Integer ano);
	
	public List<Turma> findAllBySerie(Serie serie);
	
	
	
	

}
