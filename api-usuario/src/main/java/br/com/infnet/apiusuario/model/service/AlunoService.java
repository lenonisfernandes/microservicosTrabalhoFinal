package br.com.infnet.apiusuario.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiusuario.model.domain.Aluno;
import br.com.infnet.apiusuario.model.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	public void incluir(Aluno aluno) {
		alunoRepository.save(aluno);
	}
	
	public void excluir(Integer id) {
		alunoRepository.deleteById(id);
	}
	
	public void editar(Aluno aluno) {
		Aluno old = alunoRepository.findById(aluno.getId()).get();
		if (old!=null) {
			old = aluno;
			alunoRepository.save(old);
		}
	}
	
	public List<Aluno> obterLista() {
		return (List<Aluno>) alunoRepository.findAll();
	}
	
	public Aluno obterPorId(Integer id) {
		return alunoRepository.findById(id).get();
	}
	
	public Aluno validar(String login, String senha) {
		return alunoRepository.findByLoginAndSenha(login, senha);
	}

}
