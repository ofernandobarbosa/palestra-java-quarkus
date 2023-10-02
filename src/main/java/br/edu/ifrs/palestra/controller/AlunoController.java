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
        List<Aluno> alunos = alunoService.getAll();
        return alunos;
    }

    @GET
    @Path("/{id}")
    public Aluno getById(@PathParam("id") int id) {
        Aluno aluno = alunoService.getById(id);
        return aluno;
    }

    @POST
    @Transactional
    public Aluno save(AlunoDTO alunoDto) {
        Aluno aluno = alunoService.save(alunoDto);
        return aluno;
    }

    @POST
    @Path("/{id}")
    @Transactional
    public Aluno update(@PathParam("id") int id, AlunoDTO alunoDto) {
        Aluno aluno = alunoService.update(id, alunoDto);
        return aluno;
    }
    
    @DELETE
    public Boolean delete(@PathParam("id") int id) {
        boolean deletado = alunoService.delete(id);        
        return deletado;
    }
}