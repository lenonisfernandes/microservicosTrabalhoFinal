package br.com.infnet.apiturma.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.apiturma.model.domain.Serie;
import br.com.infnet.apiturma.model.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	SerieRepository serieRepository;
	
	public void incluir(Serie serie) {
		serieRepository.save(serie);
	}
	
	public void excluir(Integer id) {
		serieRepository.deleteById(id);
	}
	
	public List<Serie> obterLista() {
		return (List<Serie>) serieRepository.findAll();
	}
	
	public Serie obterById(Integer id) {
		return serieRepository.findById(id).get();
	}
	
	public void editar(Serie serie) {
		Serie old = serieRepository.findById(serie.getId()).get();
		if (old!=null) {
			old = serie;
			serieRepository.save(old);
		}
	}

}
