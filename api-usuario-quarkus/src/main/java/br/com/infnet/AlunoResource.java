package br.com.infnet;

import java.net.URI;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.infnet.model.Aluno;

@Path("/aluno")
public class AlunoResource {
	
	@GET
	@Path("/listar")
	public Response listAll() {
		if(Aluno.listAll().size()>0) {
			return Response.ok(Aluno.listAll()).build();
		}
		else {
			return Response.noContent().build();
		}
	}
	
	@GET
	@Path("/listar/{id}")
	public Response getById(Long id) {
		return Aluno.findByIdOptional(id)
				.map(aluno -> Response.ok(aluno).build())
				.orElse(Response.noContent().build());
	}
	
	@POST
	@Transactional
	@Path("/incluir")
	public Response create(Aluno aluno) {
		aluno.persist();
		return Response.created(URI.create("/aluno/" + aluno.id)).build();
	}
	
	@PUT
	@Path("/editar/{id}")
	@Transactional
	public Response update(Long id, Aluno aluno) {
		Aluno entity = Aluno.findById(id);
		
		if(entity == null) {
			return Response.noContent().build();
		}
		
		entity.copiar(aluno);
		
		return Response.ok(aluno).build();
	}
	
	@DELETE
	@Path("/excluir/{id}")
	@Transactional
	public Response delete(Long id) {
		Aluno entity = Aluno.findById(id);
		
		if(entity == null) {
			return Response.noContent().build();
		}
		
		entity.delete();
		
		return Response.ok().build();
	}
	
	
	
	

}
