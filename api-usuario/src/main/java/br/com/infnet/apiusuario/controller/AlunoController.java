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

import br.com.infnet.apiusuario.model.domain.Aluno;
import br.com.infnet.apiusuario.model.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	AlunoService alunoService;
	
	@PostMapping("/incluir")
	public void incluir(@RequestBody Aluno aluno) {
		alunoService.incluir(aluno);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable Integer id) {
		alunoService.excluir(id);
	}
	
	@PutMapping("/editar")
	public void editar(@RequestBody Aluno aluno) {
		alunoService.editar(aluno);
	}
	
	@GetMapping("/listar")
	public List<Aluno> getAll() {
		return alunoService.obterLista();
	}
	
	@GetMapping("/listar/{id}")
	public Aluno getById(@PathVariable Integer id) {
		return alunoService.obterPorId(id);
	}
	
	@PostMapping("/validar")
	public Aluno validar(@RequestParam String login, @RequestParam String senha) {
		System.out.println(login);
		System.out.println(senha);
		return alunoService.validar(login, senha);
	}

}
