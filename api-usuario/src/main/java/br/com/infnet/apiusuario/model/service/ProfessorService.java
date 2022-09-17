package br.com.infnet.apiusuario.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiusuario.model.domain.Professor;
import br.com.infnet.apiusuario.model.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepository professorRepository;
	
	public void incluir(Professor professor) {
		professorRepository.save(professor);
	}
	
	public void excluir(Integer id) {
		professorRepository.deleteById(id);
	}
	
	public void editar(Professor professor) {
		Professor old = professorRepository.findById(professor.getId()).get();
		if (old!=null) {
			old = professor;
			professorRepository.save(old);
		}
	}
	
	public List<Professor> obterLista() {
		return (List<Professor>) professorRepository.findAll();
	}
	
	public Professor obterPorId(Integer id) {
		return professorRepository.findById(id).get();
	}
	
	public Professor validar(String login, String senha) {
		return professorRepository.findByLoginAndSenha(login, senha);
	}

}
