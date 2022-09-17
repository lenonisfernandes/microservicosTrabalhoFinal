package br.com.infnet.apiturma.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	public void incluir(Disciplina disciplina) {
		disciplinaRepository.save(disciplina);
	}
	
	public void excluir(Integer id) {
		disciplinaRepository.deleteById(id);
	}
	
	public List<Disciplina> obterLista() {
		return (List<Disciplina>) disciplinaRepository.findAll();
	}
	
	public Disciplina obterById(Integer id) {
		return disciplinaRepository.findById(id).get();
	}
	
	public void editar(Disciplina disciplina) {
		Disciplina old = disciplinaRepository.findById(disciplina.getId()).get();
		if (old!=null) {
			old = disciplina;
			disciplinaRepository.save(old);
		}
	}

}
