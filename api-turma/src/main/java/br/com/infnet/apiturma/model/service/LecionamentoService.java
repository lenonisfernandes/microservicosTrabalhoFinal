package br.com.infnet.apiturma.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.domain.Lecionamento;
import br.com.infnet.apiturma.model.domain.Professor;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.repository.LecionamentoRepository;

@Service
public class LecionamentoService {
	
	@Autowired
	LecionamentoRepository lecionamentoRepository;
	
	public void incluir(Lecionamento lecionamento) {
		lecionamentoRepository.save(lecionamento);
	}
	
	public void excluir(Integer id) {
		lecionamentoRepository.deleteById(id);
	}
	
	public List<Lecionamento> obterLista() {
		return (List<Lecionamento>) lecionamentoRepository.findAll();
	}
	
	public Lecionamento obterById(Integer id) {
		return lecionamentoRepository.findById(id).get();
	}
	
	public void editar(Lecionamento lecionamento) {
		Lecionamento old = lecionamentoRepository.findById(lecionamento.getId()).get();
		if (old!=null) {
			old = lecionamento;
			lecionamentoRepository.save(old);
		}
	}
	
	public void enturmarProfessor(Turma turma, Disciplina disciplina, 
			Professor professor) {
		Lecionamento lecionamento = new Lecionamento();
		lecionamento.setAtivo(true);
		lecionamento.setDisciplina(disciplina);
		lecionamento.setEntrada(LocalDate.now());
		lecionamento.setProfessor(professor);
		lecionamento.setTurma(turma);
		incluir(lecionamento);
	}
	
	public void trocarProfessor(Turma turma, Disciplina disciplina,
			Professor professor) {
		Lecionamento oldLec = lecionamentoRepository
				.findByAtivoAndDisciplinaAndTurma(true, disciplina, turma);
		if(oldLec.getProfessor()!=professor) {
			Lecionamento newLec = new Lecionamento();
			newLec.setProfessor(professor);
			newLec.setAtivo(true);
			newLec.setDisciplina(disciplina);
			newLec.setEntrada(LocalDate.now());
			newLec.setTurma(turma);
			oldLec.setAtivo(false);
			lecionamentoRepository.save(newLec);
			lecionamentoRepository.save(oldLec);
			
		}
	}
	
	public List<Professor> professoresPorTurma(Turma turma) {
		return lecionamentoRepository.findByAtivoAndTurma(true, turma).stream()
				.map(lec -> lec.getProfessor()).collect(Collectors.toList());
	}
	
	public List<Lecionamento> turmasPorProfessor(Professor professor) {
		return lecionamentoRepository.findByAtivoAndProfessor(true, professor);
	}
	
	public List<Lecionamento> obterPorAtivoEProfessorETurma(Boolean ativo,
			Professor professor, Turma turma) {
		return lecionamentoRepository.findByAtivoAndProfessorAndTurma(
				ativo, professor, turma);
	}

}
