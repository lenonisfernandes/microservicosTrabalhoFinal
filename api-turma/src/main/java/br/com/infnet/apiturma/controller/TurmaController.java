package br.com.infnet.apiturma.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.infnet.apiturma.model.domain.Serie;
import br.com.infnet.apiturma.model.domain.Turma;
import br.com.infnet.apiturma.model.service.SerieService;
import br.com.infnet.apiturma.model.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	
	@Autowired
	TurmaService turmaService;
	
	@Autowired
	SerieService serieService;
	
	@PostMapping("/incluir")
	public ResponseEntity<String> incluir(@RequestBody Turma turma) {
		try {
			turmaService.incluir(turma);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Turma criada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na criação da turma.");
		}
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		try {
			turmaService.excluir(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Turma excluída com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na exclusão da turma.");
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Turma>> obterLista() {
		try {
			return ResponseEntity.ok(turmaService.obterLista());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Nenhuma turma encontrada.");
		}	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Turma> obterById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(turmaService.obterById(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Turma não encontrada.");
		}
		
	}
	
	@PutMapping("/editar")
	public ResponseEntity<String> editar(@RequestBody Turma turma) {
		try {
			turmaService.editar(turma);
			return ResponseEntity.ok("Turma editada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na edição da turma.");
		} 
	}
	
	@GetMapping("/listar/ano/{ano}")
	public ResponseEntity<List<Turma>> obterPorAno(@PathVariable Integer ano) {
		
		List<Turma> turmas = turmaService.obterPorAno(ano);
		
		if(turmas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Nenhuma turma encontrada para esse ano.");
		}
		else {
			return ResponseEntity.ok(turmas);
		} 
	}
	
	@GetMapping("/listar/serie/{idSerie}")
	public ResponseEntity<List<Turma>> obterPorSerie(@PathVariable Integer idSerie) {
		Serie serie = serieService.obterById(idSerie);
		
		if (serie==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Série não cadastrada.");
		}
		
		List<Turma> turmas = turmaService.obterPorSerie(serie);
		
		if(turmas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, 
					"Nenhuma turma encontrada para essa série.");
		}
		
		else {
			return ResponseEntity.ok(turmas);
		} 
	}

}
