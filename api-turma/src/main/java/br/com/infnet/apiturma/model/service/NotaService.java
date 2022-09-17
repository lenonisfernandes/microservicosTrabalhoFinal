package br.com.infnet.apiturma.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiturma.model.domain.Aluno;
import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.domain.Nota;
import br.com.infnet.apiturma.model.domain.PeriodoEnum;
import br.com.infnet.apiturma.model.repository.NotaRepository;

@Service
public class NotaService {
	
	@Autowired
	NotaRepository notaRepository;
	
	public void incluir(Nota nota) {
		notaRepository.save(nota);
	}
	
	public void excluir(Integer id) {
		notaRepository.deleteById(id);
	}
	
	public List<Nota> obterLista() {
		return (List<Nota>) notaRepository.findAll();
	}
	
	public Nota obterById(Integer id) {
		return notaRepository.findById(id).get();
	}
	
	public void editar(Nota nota) {
		Nota old = notaRepository.findById(nota.getId()).get();
		if (old!=null) {
			old = nota;
			notaRepository.save(old);
		}
	}
	
	public List<Nota> listarPorDisciplina(Disciplina disciplina, Integer ano) {
		return notaRepository.findByDisciplinaAndAno(disciplina, ano);
	}
	
	public List<Nota> listarPorDisciplinaEPeriodo(Disciplina disciplina, 
			PeriodoEnum periodo, Integer ano) {
		return notaRepository.findByDisciplinaAndPeriodoAndAno(
				disciplina, periodo, ano);
	}
	
	public Nota obterNota(
			Aluno aluno, Disciplina disciplina, Integer ano, PeriodoEnum periodo) {
		return notaRepository.findByAlunoAndDisciplinaAndPeriodoAndAno(
				aluno, disciplina, periodo, ano);
	}

}
