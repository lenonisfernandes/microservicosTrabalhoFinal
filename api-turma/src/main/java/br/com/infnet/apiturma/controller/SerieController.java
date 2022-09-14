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
import br.com.infnet.apiturma.model.service.SerieService;

@RestController
@RequestMapping("/serie")
public class SerieController {
	
	@Autowired
	SerieService serieService;
	
	@PostMapping("/incluir")
	public ResponseEntity<String> incluir(@RequestBody Serie serie) {
		try {
			serieService.incluir(serie);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Serie criada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na criação da serie.");
		}
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		try {
			serieService.excluir(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Serie excluída com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na exclusão da serie.");
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Serie>> obterLista() {
		try {
			return ResponseEntity.ok(serieService.obterLista());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Nenhuma serie encontrada.");
		}	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Serie> obterById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(serieService.obterById(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"Serie não encontrada.");
		}
		
	}
	
	@PutMapping("/editar")
	public ResponseEntity<String> editar(@RequestBody Serie serie) {
		try {
			serieService.editar(serie);
			return ResponseEntity.ok("Serie editada com sucesso.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Problemas na edição da serie.");
		} 
	}

}
