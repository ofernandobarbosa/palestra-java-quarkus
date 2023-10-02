package br.edu.ifrs.palestra.controller;

import java.util.List;

import br.edu.ifrs.palestra.dto.TurmaDTO;
import br.edu.ifrs.palestra.model.Turma;
import br.edu.ifrs.palestra.service.interfaces.TurmaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/turma")
public class TurmaController {

    @Inject
    private TurmaService turmaService;
    
    @GET
    public List<Turma> getAll() {
        List<Turma> turmas = turmaService.getAll();
        return turmas;
    }

    @GET
    @Path("/{id}")
    public Turma getById(@PathParam("id") int id) {
        Turma turma = turmaService.getById(id);
        return turma;
    }

    @POST
    public Turma save(TurmaDTO turmaDto) {
        Turma turma = turmaService.save(turmaDto);
        return turma;
    }

    @PUT
    @Path("/{id}")
    public Turma update(@PathParam("id") int id, TurmaDTO turmaDto) {
        Turma turma = turmaService.update(id, turmaDto);
        return turma;
    }
    
    @DELETE
    @Path("/{id}")
    public Boolean delete(@PathParam("id") int id) {
        boolean deletado = turmaService.delete(id);        
        return deletado;
    }
    
    @POST
    @Path("/{turma_id}/matricular")
    public Turma matricular(@PathParam("turma_id") int turma_id, @QueryParam("id") int aluno_id) {
        Turma turma = turmaService.matricular(turma_id, aluno_id);
        return turma;
    }

    @DELETE
    @Path("/{turma_id}/cancelar-matricula")
    public Turma cancelarMatricula(@PathParam("turma_id") int turma_id, @QueryParam("id") int aluno_id) {
        Turma turma = turmaService.cancelarMatricula(turma_id, aluno_id);
        return turma;
    }
    
}
