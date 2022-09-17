package br.com.infnet.appnotas.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import br.com.infnet.appnotas.model.domain.Aluno;
import br.com.infnet.appnotas.model.domain.Professor;
import br.com.infnet.appnotas.model.service.ProfessorService;
import br.com.infnet.appnotas.model.service.TurmaService;

@SessionAttributes({"professor", "aluno"})
@Controller
public class AcessoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8145235072844146529L;

	@Autowired
	private ProfessorService professorService;
	@Autowired
	private TurmaService turmaService;

	@GetMapping(value = "/login")
	public String telaLogin() {
		return "login";
	}

	@GetMapping(value = "/logout")
	public String telaLogout(HttpSession session, SessionStatus status) {
		status.setComplete();
		session.removeAttribute("aluno");
		session.removeAttribute("professor");
		return "redirect:/";
	}

	@GetMapping(value = "/")
	public String telaIndex() {
		return "index";
	}

	@PostMapping(value = "/login")
	public String acessar(Model model, @RequestParam String login, @RequestParam String senha) {

		Professor professor = professorService.validarProfessor(login, senha);


		if (professor != null) {
			model.addAttribute("professor", professor);
			return "index";
		} else {
			Aluno aluno = professorService.validarAluno(login, senha);

			if (aluno != null) {
				model.addAttribute("aluno", aluno);
				return "redirect:/aluno/boletim";
			} else {
				model.addAttribute("mensagem", "Impossível realizar a autenticação: " + login + "!");
				return "login";
			}

		}

	}
	
}
