package br.com.infnet.apiturma.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.domain.Lecionamento;
import br.com.infnet.apiturma.model.domain.Professor;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.service.DisciplinaService;
import br.com.infnet.apiturma.model.service.LecionamentoService;
import br.com.infnet.apiturma.model.service.ProfessorService;
import br.com.infnet.apiturma.model.service.TurmaService;

@RestController
@RequestMapping
public class LecionamentoController {
	
	@Autowired
	LecionamentoService lecionamentoService;
	@Autowired
	ProfessorService professorService;
	@Autowired
	TurmaService turmaService;
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping("/professor/enturmar")
	public ResponseEntity<String> enturmarProfessor(
			@PathParam("idTurma") Integer idTurma,
			@PathParam("idProfessor") Integer idProfessor,
			@PathParam("idDisciplina") Integer idDisciplina) {
		
		Turma turma = turmaService.obterById(idTurma);
		System.out.println(turma.getCodigo());
		Professor professor = professorService.obterById(idProfessor);
		System.out.println(professor.getNome());
		Disciplina disciplina = disciplinaService.obterById(idDisciplina);
		System.out.println(disciplina.getNome());
		
		if (turma==null || disciplina==null || professor==null) {
			ResponseEntity.noContent();
		}
		lecionamentoService.enturmarProfessor(turma, disciplina, professor);
		return ResponseEntity.ok("Professor enturmado com sucesso.");
	}
	
	@GetMapping("/turma/trocarProfessor")
	public ResponseEntity<String> trocarProfessor(
			@PathParam("idTurma") Integer idTurma,
			@PathParam("idProfessor") Integer idProfessor,
			@PathParam("idDisciplina") Integer idDisciplina) {
		
		Turma turma = turmaService.obterById(idTurma);
		Professor professor = professorService.obterById(idProfessor);
		Disciplina disciplina = disciplinaService.obterById(idDisciplina);
		
		if (turma==null || disciplina==null || professor==null) {
			ResponseEntity.noContent();
		}
		lecionamentoService.trocarProfessor(turma, disciplina, professor);
		return ResponseEntity.ok("Troca efetuada com sucesso.");
	}
	
	@GetMapping("/turma/professores/{id}")
	public ResponseEntity<List<Professor>> professoresPorTurma(
			@PathVariable Integer id) {
		Turma turma = turmaService.obterById(id);
		if (turma==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		List<Professor> professores = lecionamentoService.professoresPorTurma(turma);
		if(professores.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(professores);
		}
	}
	
	@GetMapping("/professor/turma/{id}")
	public ResponseEntity<List<Lecionamento>> turmaPorProfessores(
			@PathVariable Integer id) {
		Professor professor = professorService.obterById(id);
		if (professor==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		List<Lecionamento> lecionamentos = 
				lecionamentoService.turmasPorProfessor(professor);
		if(lecionamentos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(lecionamentos);
		}
	}
	
	@GetMapping("/lecionamento/listar/{id}")
	public ResponseEntity<Lecionamento> obterById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(lecionamentoService.obterById(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Lecionamento não encontrado.");
		}
		
	}
	

	

}
