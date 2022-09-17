package br.com.infnet.apiusuario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiusuario.model.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	
	public Aluno findByLoginAndSenha(String login, String senha);


}
