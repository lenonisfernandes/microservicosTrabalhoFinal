package br.com.infnet.apiusuario.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiusuario.model.domain.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Integer>{

}
