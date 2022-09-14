package br.com.infnet.apiturma.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.infnet.apiturma.model.domain.Aluno;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.service.AlunoServiceRestTemplate;
import br.com.infnet.apiturma.model.service.EnturmamentoService;
import br.com.infnet.apiturma.model.service.TurmaService;

@RestController
public class EnturmamentoController {
	
	@Autowired
	EnturmamentoService enturmamentoService;
	@Autowired
	TurmaService turmaService;
	@Autowired
	AlunoServiceRestTemplate alunoServiceRest;
	
	@GetMapping("/aluno/enturmar")
	public ResponseEntity<String> enturmar(@PathParam("idTurma") Integer idTurma, 
			@PathParam("idAluno") Integer idAluno) {
		Turma turma = turmaService.obterById(idTurma);
		Aluno aluno = alunoServiceRest.obterById(idAluno);
		if (turma==null || aluno==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		try {
			enturmamentoService.enturmar(turma, aluno);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} 
	}
	
	@GetMapping("/turma/trocar")
	public ResponseEntity<String> trocarTurma(@PathParam("idTurma") Integer idTurma, 
			@PathParam("idAluno") Integer idAluno) {
		
		Turma turma = turmaService.obterById(idTurma);
		Aluno aluno = alunoServiceRest.obterById(idAluno);
		if (turma==null || aluno==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		try {
			enturmamentoService.trocarTurma(aluno, turma);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} 

	}
	
	@GetMapping("/aluno/acharTurma/{idAluno}")
	public ResponseEntity<Turma> acharTurmaAtiva(@PathVariable Integer idAluno) {
		Aluno aluno = alunoServiceRest.obterById(idAluno);
		if (aluno==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		Turma turma = enturmamentoService.acharTurmaAtiva(aluno);
		
		if (turma==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		else {
			return ResponseEntity.ok(turma);
		}
		
	}
	
	@GetMapping("/turma/obterAlunos/{idTurma}")
	public ResponseEntity<List<Aluno>> obterAlunosPorTurma(
			@PathVariable Integer idTurma) {
		Turma turma = turmaService.obterById(idTurma);
		if (turma==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		List<Aluno> alunos = enturmamentoService.obterAlunosPorTurma(turma);
		
		if(alunos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		else {
			return ResponseEntity.ok(alunos);
		}
		
	}

}
