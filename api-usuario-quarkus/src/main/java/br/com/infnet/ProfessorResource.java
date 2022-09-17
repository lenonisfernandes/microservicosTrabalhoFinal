package br.com.infnet;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.infnet.model.Professor;

@Path("/professor")
public class ProfessorResource {
	
	@GET
	@Path("/listar")
	public List<Professor> listAll() {
		return Professor.listAll();
	}
	
	@GET
	@Path("/listar/{id}")
	public Response getById(Long id) {
		return Professor.findByIdOptional(id)
				.map(professor -> Response.ok(professor).build())
				.orElse(Response.noContent().build());
	}
	
	@POST
	@Transactional
	@Path("/incluir")
	public Response create(Professor professor) {
		professor.persist();
		return Response.created(URI.create("/professor/" + professor.id))
				.build();
	}
	
	@PUT
	@Path("/editar/{id}")
	@Transactional
	public Response update(Long id, Professor professor) {
		Professor entity = Professor.findById(id);
		
		if(entity == null) {
			return Response.noContent().build();
		}
		
		entity.copiar(professor);
		
		return Response.ok(professor).build();
	}
	
	@DELETE
	@Path("/excluir/{id}")
	@Transactional
	public Response delete(Long id) {
		Professor entity = Professor.findById(id);
		
		if(entity == null) {
			return Response.noContent().build();
		}
		
		entity.delete();
		
		return Response.ok().build();
	}

}
