package br.com.infnet.appnotas.model.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.infnet.appnotas.model.domain.Aluno;
import br.com.infnet.appnotas.model.domain.Disciplina;
import br.com.infnet.appnotas.model.domain.Lecionamento;
import br.com.infnet.appnotas.model.domain.Nota;
import br.com.infnet.appnotas.model.domain.PeriodoEnum;
import br.com.infnet.appnotas.model.domain.Turma;

@FeignClient("TURMA")
public interface TurmaService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/turma/listar")
	List<Turma> obterListaTurma();
	
	@RequestMapping(method = RequestMethod.GET, value = "/turma/listar/{id}")
	Turma obterByIdTurma(@PathVariable Integer id);
	
	@RequestMapping(method = RequestMethod.GET, value = "/lecionamento/listar/{id}")
	Lecionamento obterByIdLecionamento(@PathVariable Integer id);
	
	@RequestMapping(method = RequestMethod.PUT, value = "/turma/editar")
	void editarTurma(@RequestBody Turma turma);
	
	@RequestMapping(method = RequestMethod.GET, value = "/professor/turma/{id}")
	List<Lecionamento> turmaPorProfessores(@PathVariable Integer id);
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/turma/obterAlunos/{idTurma}")
	List<Aluno> obterAlunosPorTurma(@PathVariable Integer idTurma);
	
	@RequestMapping(method = RequestMethod.GET, value = "/nota/listar/disciplina")
	List<Nota> listarNotaPorDisciplinaETurma(
			@RequestParam("idDisciplina") Integer idDisciplina, 
			@RequestParam("ano") Integer ano,
			@RequestParam("idTurma") Integer idTurma);
	
	@RequestMapping(method = RequestMethod.POST, value = "/nota/obter")
	Nota obterNota(
			@RequestParam Integer idAluno, 
			@RequestParam Integer idDisciplina, 
			@RequestParam Integer ano, 
			@RequestParam PeriodoEnum periodo);
	
	@RequestMapping(method = RequestMethod.POST, value = "/nota/incluir")
	String incluir(@RequestBody Nota nota);
	
	@RequestMapping(method = RequestMethod.GET, value = "/disciplina/listar")
	List<Disciplina> obterListaDisciplinas();

}
