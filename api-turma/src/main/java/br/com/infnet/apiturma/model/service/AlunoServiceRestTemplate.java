package br.com.infnet.apiturma.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.infnet.apiturma.model.domain.Aluno;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AlunoServiceRestTemplate {
	
	@Autowired
	RestTemplate restTemplate;
	
	@CircuitBreaker(name = "alunoService", fallbackMethod= "getAlunoFallback")
	public Aluno obterById(Integer id) {
		Aluno aluno = restTemplate
				.getForObject("http://USUARIO/aluno/listar/{id}", Aluno.class, id);
		return aluno;
	}
	public Aluno getAlunoFallback(Exception ex) {
		System.out.println(ex.getMessage());
		return null;
	}

}
