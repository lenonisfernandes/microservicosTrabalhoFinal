package br.com.infnet.apiturma.model.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.infnet.apiturma.model.domain.Professor;

@FeignClient("USUARIO")
public interface ProfessorService {

	@RequestMapping(method = RequestMethod.GET, value = "/professor/listar")
	List<Professor> obterLista();
	
	@RequestMapping(method = RequestMethod.GET, value = "/professor/listar/{id}")
	Professor obterById(@PathVariable Integer id);
	
	@RequestMapping(method = RequestMethod.PUT, value = "/professor/editar")
	void editar(@RequestBody Professor professor);
	
}
