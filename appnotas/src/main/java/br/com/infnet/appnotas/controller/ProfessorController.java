package br.com.infnet.appnotas.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.infnet.appnotas.model.domain.Aluno;
import br.com.infnet.appnotas.model.domain.Disciplina;
import br.com.infnet.appnotas.model.domain.Lecionamento;
import br.com.infnet.appnotas.model.domain.Nota;
import br.com.infnet.appnotas.model.domain.PeriodoEnum;
import br.com.infnet.appnotas.model.domain.Professor;
import br.com.infnet.appnotas.model.service.TurmaService;

@Controller
@SessionAttributes("alunosArray")
public class ProfessorController {

	@Autowired
	TurmaService turmaService;

	@GetMapping(value = "/nota/visualizar")
	public String escolherTurmaVisualizar(Model model, @SessionAttribute("professor") Professor professor) {

		model.addAttribute("turmas", turmaService.turmaPorProfessores(professor.getId()));
		return "professor/listarNotas";
	}

	@PostMapping(value = "/nota/visualizar")
	public String visualizarNotas(Model model, @SessionAttribute("professor") Professor professor,
			@RequestParam Integer idLecionamento) {

		model.addAttribute("turmas", turmaService.turmaPorProfessores(professor.getId()));

		Lecionamento lecionamento = turmaService.obterByIdLecionamento(idLecionamento);
		model.addAttribute("lecionamento", lecionamento);

		List<Aluno> alunos = turmaService.obterAlunosPorTurma(lecionamento.getTurma().getId());

		if (alunos != null) {
			alunos.stream().sorted(Comparator.comparing(Aluno::getNome)).collect(Collectors.toList());
		} else {
			alunos = new ArrayList<Aluno>();
		}

		Integer idDisciplina = lecionamento.getDisciplina().getId();

		StringBuilder table = new StringBuilder();

		alunos.forEach(aluno -> {

			Nota n1 = turmaService.obterNota(aluno.getId(), idDisciplina, 2022, PeriodoEnum.BIM_1);
			Nota n2 = turmaService.obterNota(aluno.getId(), idDisciplina, 2022, PeriodoEnum.BIM_2);
			Nota r1 = turmaService.obterNota(aluno.getId(), idDisciplina, 2022, PeriodoEnum.REC_1);
			Nota n3 = turmaService.obterNota(aluno.getId(), idDisciplina, 2022, PeriodoEnum.BIM_3);
			Nota n4 = turmaService.obterNota(aluno.getId(), idDisciplina, 2022, PeriodoEnum.BIM_4);
			Nota r2 = turmaService.obterNota(aluno.getId(), idDisciplina, 2022, PeriodoEnum.REC_2);

			List<Nota> notasNull = Arrays.asList(n1, n2, r1, n3, n4, r2);
			for (Nota n : notasNull) {
				if (n == null)
					n = new Nota();
			}

			table.append("<tr><td>");
			table.append(aluno.getNome());
			table.append("</td><td>");
			table.append(exibirNota(n1));
			table.append("</td><td>");
			table.append(exibirNota(n2));
			table.append("</td><td>");
			table.append(exibirNota(r1));
			table.append("</td><td>");
			table.append(calcularMediaSemestral(n1, n2, r1));
			table.append("</td><td>");
			table.append(exibirNota(n3));
			table.append("</td><td>");
			table.append(exibirNota(n4));
			table.append("</td><td>");
			table.append(exibirNota(r2));
			table.append("</td><td>");
			table.append(calcularMediaFinal(n1, n2, n3, n4, r1, r2));
			table.append("</td>");
		});

		model.addAttribute("table", table.toString());

		return "professor/listarNotas";
	}

	@GetMapping(value = "/nota/cadastrar")
	public String escolherTurmaCadastrar(Model model, @SessionAttribute("professor") Professor professor) {

		model.addAttribute("turmas", turmaService.turmaPorProfessores(professor.getId()));

		return "professor/cadastrarNotas";
	}

	@PostMapping(value = "/nota/cadastrar")
	public String cadastrarNotas(Model model, @SessionAttribute("professor") Professor professor,
			@RequestParam Integer idLecionamento, @RequestParam PeriodoEnum periodo) {

		model.addAttribute("turmas", turmaService.turmaPorProfessores(professor.getId()));

		Lecionamento lecionamento = turmaService.obterByIdLecionamento(idLecionamento);
		model.addAttribute("lecionamento", lecionamento);
		model.addAttribute("periodo", periodo);

		List<Aluno> alunos = turmaService.obterAlunosPorTurma(lecionamento.getTurma().getId());

		if (alunos != null) {
			alunos = alunos.stream().sorted(Comparator.comparing(Aluno::getNome)).collect(Collectors.toList());
			;
		} else {
			alunos = new ArrayList<Aluno>();
		}

		Aluno[] alunosArray = new Aluno[alunos.size()];
		Double[] notasArray = new Double[alunos.size()];
		int i = 0;
		for (Aluno aluno : alunos) {
			alunosArray[i++] = aluno;
		}
		model.addAttribute("alunosArray", alunosArray);
		StringBuilder form = new StringBuilder();

		for (Aluno aluno : alunosArray) {
			form.append("<tr><td>");
			form.append("<label>");
			form.append(aluno.getNome());
			form.append("</label></td><td>");
			form.append(
					"<input type='number' class='form-control' name='notasArray' min='0' max='10' step='0.1' value='");
			Nota nota = turmaService.obterNota(aluno.getId(), lecionamento.getDisciplina().getId(), 2022, periodo);
			if (nota == null) {
				form.append("0");
			} else {
				form.append(nota.getValor().toString());
			}
			form.append("'>");
			form.append("</td></tr>");
		}

		model.addAttribute("form", form.toString());

		return "professor/cadastrarNotas";

	}

	@PostMapping(value = "/nota/enviar")
	public String enviarNotas(Model model, @SessionAttribute("professor") Professor professor,
			@RequestParam Integer idLecionamento, @RequestParam String[] notasArray, @RequestParam PeriodoEnum periodo,
			@SessionAttribute("alunosArray") Aluno[] alunosArray) {

		Lecionamento lecionamento = turmaService.obterByIdLecionamento(idLecionamento);

		for (int i = 0; i < alunosArray.length; i++) {
			System.out.println(alunosArray[i].getNome() + " - " + Double.valueOf(notasArray[i]));

			Nota nota = turmaService.obterNota(alunosArray[i].getId(), lecionamento.getDisciplina().getId(), 2022,
					periodo);

			if (nota == null) {
				nota = new Nota();
				nota.setAluno(alunosArray[i]);
				nota.setAno(2022);
				nota.setDisciplina(lecionamento.getDisciplina());
				nota.setPeriodo(periodo);
			}

			nota.setValor(Double.valueOf(notasArray[i]));
			turmaService.incluir(nota);
		}

		return visualizarNotas(model, professor, idLecionamento);
	}

	String obterNotaAsString(Integer idAluno, Integer idDisciplina, Integer ano, PeriodoEnum periodo) {
		Nota nota = turmaService.obterNota(idAluno, idDisciplina, ano, periodo);
		if (nota == null)
			return "-";
		else
			return nota.getValor().toString();

	}

	@GetMapping(value = "/aluno/boletim")
	public String boletim(Model model, @SessionAttribute("aluno") Aluno aluno) {
		List<Disciplina> disciplinas = turmaService.obterListaDisciplinas();

		if (disciplinas != null) {
			disciplinas = disciplinas.stream().sorted(Comparator.comparing(Disciplina::getNome))
					.collect(Collectors.toList());
		} else {
			disciplinas = new ArrayList<Disciplina>();
		}

		StringBuilder table = new StringBuilder();

		for (Disciplina d : disciplinas) {
			Nota n1 = turmaService.obterNota(aluno.getId(), d.getId(), 2022, PeriodoEnum.BIM_1);
			Nota n2 = turmaService.obterNota(aluno.getId(), d.getId(), 2022, PeriodoEnum.BIM_2);
			Nota r1 = turmaService.obterNota(aluno.getId(), d.getId(), 2022, PeriodoEnum.REC_1);
			Nota n3 = turmaService.obterNota(aluno.getId(), d.getId(), 2022, PeriodoEnum.BIM_3);
			Nota n4 = turmaService.obterNota(aluno.getId(), d.getId(), 2022, PeriodoEnum.BIM_4);
			Nota r2 = turmaService.obterNota(aluno.getId(), d.getId(), 2022, PeriodoEnum.REC_2);

			List<Nota> notasNull = Arrays.asList(n1, n2, r1, n3, n4, r2);
			for (Nota n : notasNull) {
				if (n == null)
					n = new Nota();
			}

			table.append("<tr><td>");
			table.append(d.getNome());
			table.append("</td><td>");
			table.append(exibirNota(n1));
			table.append("</td><td>");
			table.append(exibirNota(n2));
			table.append("</td><td>");
			table.append(exibirNota(r1));
			table.append("</td><td>");
			table.append(calcularMediaSemestral(n1, n2, r1));
			table.append("</td><td>");
			table.append(exibirNota(n3));
			table.append("</td><td>");
			table.append(exibirNota(n4));
			table.append("</td><td>");
			table.append(exibirNota(r2));
			table.append("</td><td>");
			table.append(calcularMediaFinal(n1, n2, n3, n4, r1, r2));
			table.append("</td><td>");
		}

		model.addAttribute("table", table.toString());

		return "aluno/listarNotas";
	}

	String calcularMediaSemestral(Nota bim1, Nota bim2, Nota rec1) {
		if (bim1 != null && bim2 != null) {
			Double media = (bim1.getValor() + bim2.getValor()) / 2;
			if (rec1 != null && rec1.getValor() > media) {
				Double res = (media + rec1.getValor()) / 2;
				return String.format("%.1f", res);
			} else {
				return String.format("%.1f", media);
			}
		}
		return "-";
	}

	String calcularMediaFinal(Nota bim1, Nota bim2, Nota bim3, Nota bim4, Nota rec1, Nota rec2) {
		Double media1 = null;
		Double media2 = null;

		try {
			media1 = Double.valueOf(calcularMediaSemestral(bim1, bim2, rec1));
			media2 = Double.valueOf(calcularMediaSemestral(bim3, bim4, rec2));

			Double mediaFinal = (media1 + media2) / 2;
			return String.format("%.1f", mediaFinal);

		} catch (Exception e) {
			return "-";
		}

	}
	
	String exibirNota(Nota nota) {
		if(nota==null) {
			return "-";
		}
		else {
			return nota.getValor().toString();
		}
	}

}
