package br.com.infnet.apiturma.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.infnet.apiturma.model.domain.Aluno;
import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.domain.Nota;
import br.com.infnet.apiturma.model.domain.PeriodoEnum;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.service.AlunoServiceRestTemplate;
import br.com.infnet.apiturma.model.service.DisciplinaService;
import br.com.infnet.apiturma.model.service.EnturmamentoService;
import br.com.infnet.apiturma.model.service.NotaService;
import br.com.infnet.apiturma.model.service.TurmaService;

@RestController
@RequestMapping("/nota")
public class NotaController {

	@Autowired
	NotaService notaService;
	@Autowired
	DisciplinaService disciplinaService;
	@Autowired
	TurmaService turmaService;
	@Autowired
	AlunoServiceRestTemplate alunoService;
	@Autowired
	EnturmamentoService enturmamentoService;

	@PostMapping("/incluir")
	public ResponseEntity<String> incluir(@RequestBody Nota nota) {
		try {
			notaService.incluir(nota);
			return ResponseEntity.status(HttpStatus.CREATED).body("Nota criada com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problemas na criação da nota.");
		}
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		try {
			notaService.excluir(id);
			return ResponseEntity.status(HttpStatus.OK).body("Nota excluída com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problemas na exclusão da nota.");
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Nota>> obterLista() {
		try {
			return ResponseEntity.ok(notaService.obterLista());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma nota encontrada.");
		}
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<Nota> obterById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(notaService.obterById(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nota não encontrada.");
		}

	}

	@PutMapping("/editar")
	public ResponseEntity<String> editar(@RequestBody Nota nota) {
		try {
			notaService.editar(nota);
			return ResponseEntity.ok("Nota editada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problemas na edição da nota.");
		}
	}

	@GetMapping("/listar/disciplina")
	public ResponseEntity<List<Nota>> listarPorDisciplinaETurma(@PathParam("idDisciplina") Integer idDisciplina,
			@PathParam("ano") Integer ano, @PathParam("idTurma") Integer idTurma) {
		Disciplina disciplina = disciplinaService.obterById(idDisciplina);
		Turma turma = turmaService.obterById(idTurma);
		if (disciplina == null || turma == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		List<Nota> notas = notaService.listarPorDisciplina(disciplina, ano).stream()
				.filter(nota -> enturmamentoService.acharTurmaAtiva(nota.getAluno()) == turma)
				.collect(Collectors.toList());
		if (notas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(notas);
		}
	}

	@GetMapping("/listar/disciplina/periodo")
	public ResponseEntity<List<Nota>> listarPorDisciplina(@PathParam("idDisciplina") Integer idDisciplina,
			@PathParam("ano") Integer ano, @PathParam("periodo") PeriodoEnum periodo,
			@PathParam("idTurma") Integer idTurma) {
		Disciplina disciplina = disciplinaService.obterById(idDisciplina);
		Turma turma = turmaService.obterById(idTurma);
		if (disciplina == null || turma == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		List<Nota> notas = notaService.listarPorDisciplinaEPeriodo(disciplina, periodo, ano).stream()
				.filter(nota -> enturmamentoService.acharTurmaAtiva(nota.getAluno()) == turma)
				.collect(Collectors.toList());
		;
		if (notas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(notas);
		}
	}

	@PostMapping("/obter")
	public ResponseEntity<Nota> obterNota(@RequestParam Integer idAluno, @RequestParam Integer idDisciplina,
			@RequestParam Integer ano, @RequestParam PeriodoEnum periodo) {
		Aluno aluno = alunoService.obterById(idAluno);
		Disciplina disciplina = disciplinaService.obterById(idDisciplina);

		Nota nota = notaService.obterNota(aluno, disciplina, ano, periodo);

		return ResponseEntity.ok(nota);

	}

}
