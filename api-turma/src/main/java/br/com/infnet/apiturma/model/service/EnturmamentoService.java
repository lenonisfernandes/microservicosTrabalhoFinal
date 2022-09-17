package br.com.infnet.apiturma.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiturma.model.domain.Aluno;
import br.com.infnet.apiturma.model.domain.Enturmamento;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.repository.EnturmamentoRepository;

@Service
public class EnturmamentoService {
	
	@Autowired
	EnturmamentoRepository enturmamentoRepository;
	
	public void enturmar(Turma turma, Aluno aluno) {
		Enturmamento enturmamento = new Enturmamento();
		enturmamento.setAluno(aluno);
		enturmamento.setTurma(turma);
		enturmamento.setAtivo(true);
		enturmamento.setEntrada(LocalDate.now());
		enturmamentoRepository.save(enturmamento);
	}
	
	public void trocarTurma(Aluno aluno, Turma turma) {
		Enturmamento oldEntur = acharEnturmanetoAtivo(aluno);
		if (oldEntur.getTurma()!=turma) {
			Enturmamento newEntur = new Enturmamento();
			newEntur.setEntrada(LocalDate.now());
			newEntur.setTurma(turma);
			newEntur.setAluno(aluno);
			newEntur.setAtivo(true);
			oldEntur.setAtivo(false);
			enturmamentoRepository.save(newEntur);
			enturmamentoRepository.save(oldEntur);
		}
	}
	
	public Turma acharTurmaAtiva(Aluno aluno) {
		return acharEnturmanetoAtivo(aluno).getTurma();
	}
	
	public Enturmamento acharEnturmanetoAtivo(Aluno aluno) {
		return enturmamentoRepository.findByAluno(aluno).stream()
				.filter(entur -> entur.getAtivo())
				.findFirst().orElse(null);
	}
	
	public List<Aluno> obterAlunosPorTurma(Turma turma) {
		return enturmamentoRepository.findByTurmaAndAtivo(turma, true).stream()
				.map(entur -> entur.getAluno()).collect(Collectors.toList());
	}
}
