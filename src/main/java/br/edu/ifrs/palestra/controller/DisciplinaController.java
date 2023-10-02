package br.edu.ifrs.palestra.controller;

import java.util.List;

import br.edu.ifrs.palestra.dto.DisciplinaDTO;
import br.edu.ifrs.palestra.model.Disciplina;
import br.edu.ifrs.palestra.service.interfaces.DisciplinaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/disciplina")
public class DisciplinaController {
    
    @Inject
    private DisciplinaService disciplinaService;
    
    @GET
    public List<Disciplina> getAll() {
        List<Disciplina> disciplinas = disciplinaService.getAll();
        return disciplinas;
    }

    @GET
    @Path("/{id}")
    public Disciplina getById(@PathParam("id") int id) {
        Disciplina disciplina = disciplinaService.getById(id);
        return disciplina;
    }

    @POST
    public Disciplina save(DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = disciplinaService.save(disciplinaDto);
        return disciplina;
    }

    @PUT
    @Path("/{id}")
    public Disciplina update(@PathParam("id") int id, DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = disciplinaService.update(id, disciplinaDto);
        return disciplina;
    }
    
    @DELETE
    @Path("/{id}")
    public Boolean delete(@PathParam("id") int id) {
        boolean deletado = disciplinaService.delete(id);        
        return deletado;
    }
    
}
