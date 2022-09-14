package br.com.infnet.apiturma.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.infnet.apiturma.model.domain.Serie;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Integer>{

}
