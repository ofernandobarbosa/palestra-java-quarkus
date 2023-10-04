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
        return disciplinaService.getAll();
    }

    @GET
    @Path("/{id}")
    public Disciplina getById(@PathParam("id") Long id) {
        return disciplinaService.getById(id);
    }

    @POST
    public Disciplina save(DisciplinaDTO disciplinaDto) {
        return disciplinaService.save(disciplinaDto);
    }

    @PUT
    @Path("/{id}")
    public Disciplina update(@PathParam("id") Long id, DisciplinaDTO disciplinaDto) {
        return disciplinaService.update(id, disciplinaDto);
    }
    
    @DELETE
    @Path("/{id}")
    public Boolean delete(@PathParam("id") Long id) {
        return disciplinaService.delete(id);
    }
    
}
