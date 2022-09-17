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

import br.com.infnet.apiturma.model.domain.Disciplina;
import br.com.infnet.apiturma.model.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@PostMapping("/incluir")
	public ResponseEntity<String> incluir(@RequestBody Disciplina disciplina) {
		try {
			disciplinaService.incluir(disciplina);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Disciplina criada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na criação da disciplina.");
		}
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		try {
			disciplinaService.excluir(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Disciplina excluída com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na exclusão da disciplina.");
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Disciplina>> obterLista() {
		try {
			return ResponseEntity.ok(disciplinaService.obterLista());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Nenhuma disciplina encontrada.");
		}	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Disciplina> obterById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(disciplinaService.obterById(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Disciplina não encontrada.");
		}
		
	}
	
	@PutMapping("/editar")
	public ResponseEntity<String> editar(@RequestBody Disciplina disciplina) {
		try {
			disciplinaService.editar(disciplina);
			return ResponseEntity.ok("Disciplina editada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na edição da disciplina.");
		} 
	}

}
