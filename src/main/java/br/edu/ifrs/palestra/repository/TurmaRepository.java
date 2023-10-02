package br.edu.ifrs.palestra.repository;

import br.edu.ifrs.palestra.model.Turma;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TurmaRepository implements PanacheRepository<Turma> {

    public Turma saveAndReturn(Turma turma) {
        persist(turma);
        getEntityManager().flush();
        return turma;
    }
}
