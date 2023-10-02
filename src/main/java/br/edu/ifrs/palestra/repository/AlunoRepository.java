package br.edu.ifrs.palestra.repository;

import br.edu.ifrs.palestra.model.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlunoRepository implements PanacheRepository<Aluno> {

    // @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Aluno a
    // WHERE a.matricula = :matricula")
    // boolean existsByMatricula(@Param("matricula") String matricula);

    public boolean existsByMatricula(String matricula) {
        Aluno aluno = find("matricula", matricula).firstResult();
        return aluno != null;
    };

    public Aluno saveAndReturn(Aluno aluno) {
        persist(aluno);
        getEntityManager().flush();
        return aluno;
    }
}
