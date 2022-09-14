package br.com.infnet.apiturma.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiturma.model.domain.Serie;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository turmaRepository;
	
	public void incluir(Turma turma) {
		turmaRepository.save(turma);
	}
	
	public void excluir(Integer id) {
		turmaRepository.deleteById(id);
	}
	
	public List<Turma> obterLista() {
		return (List<Turma>) turmaRepository.findAll();
	}
	
	public Turma obterById(Integer id) {
		return turmaRepository.findById(id).get();
	}
	
	public void editar(Turma turma) {
		Turma old = turmaRepository.findById(turma.getId()).get();
		if (old!=null) {
			old = turma;
			turmaRepository.save(old);
		}
	}
	
	public List<Turma> obterPorAno(Integer ano) {
		return (List<Turma>) turmaRepository.findAllByAno(ano);
	}
	
	public List<Turma> obterPorSerie(Serie serie) {
		return (List<Turma>) turmaRepository.findAllBySerie(serie);
	}
	
	

}
