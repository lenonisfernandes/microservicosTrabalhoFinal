package br.com.infnet.apiusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infnet.apiusuario.model.domain.Professor;
import br.com.infnet.apiusuario.model.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@PostMapping("/incluir")
	public void incluir(@RequestBody Professor professor) {
		professorService.incluir(professor);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable Integer id) {
		professorService.excluir(id);
	}
	
	@PutMapping("/editar")
	public void editar(@RequestBody Professor professor) {
		professorService.editar(professor);
	}
	
	@GetMapping("/listar")
	public List<Professor> getAll() {
		return professorService.obterLista();
	}
	
	@GetMapping("/listar/{id}")
	public Professor getById(@PathVariable Integer id) {
		return professorService.obterPorId(id);
	}
	
	@PostMapping("/validar")
	public Professor validar(@RequestParam String login, @RequestParam String senha) {
		System.out.println(login);
		System.out.println(senha);
		return professorService.validar(login, senha);
	}

}
