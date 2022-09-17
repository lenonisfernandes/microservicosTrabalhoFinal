package br.com.infnet.appnotas.model.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.infnet.appnotas.model.domain.Aluno;
import br.com.infnet.appnotas.model.domain.Professor;

@FeignClient("USUARIO")
public interface ProfessorService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/professor/listar")
	List<Professor> obterLista();
	
	@RequestMapping(method = RequestMethod.GET, value = "/professor/listar/{id}")
	Professor obterById(@PathVariable Integer id);
	
	@RequestMapping(method = RequestMethod.PUT, value = "/professor/editar")
	void editar(@RequestBody Professor professor);
	
	@RequestMapping(method = RequestMethod.POST, value = "/professor/validar")
	Professor validarProfessor(@RequestParam String login, @RequestParam String senha);
	
	@RequestMapping(method = RequestMethod.POST, value = "/aluno/validar")
	Aluno validarAluno(@RequestParam String login, @RequestParam String senha);

}
