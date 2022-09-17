package br.com.infnet.apiturma.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiturma.model.domain.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Integer>{

}
