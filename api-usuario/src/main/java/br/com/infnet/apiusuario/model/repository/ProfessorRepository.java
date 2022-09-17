package br.com.infnet.apiusuario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiusuario.model.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
	
	public Professor findByLoginAndSenha(String login, String senha);

}
