package br.edu.ifrs.palestra.controller;

import java.util.List;

import br.edu.ifrs.palestra.dto.AlunoDTO;
import br.edu.ifrs.palestra.model.Aluno;
import br.edu.ifrs.palestra.service.interfaces.AlunoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/aluno")
public class AlunoController {

    @Inject
    private AlunoService alunoService;

    @GET
    public List<Aluno> getAll() {
        return alunoService.getAll();
    }

    @GET
    @Path("/{id}")
    public Aluno getById(@PathParam("id") Long id) {
        return alunoService.getById(id);
    }

    @POST
    @Transactional
    public Aluno save(AlunoDTO alunoDto) {
        return alunoService.save(alunoDto);
    }

    @POST
    @Path("/{id}")
    @Transactional
    public Aluno update(@PathParam("id") Long id, AlunoDTO alunoDto) {
        return alunoService.update(id, alunoDto);
    }

    @DELETE
    @Path("/{id}")
    public Boolean delete(@PathParam("id") Long id) {
        return alunoService.delete(id);
    }
}