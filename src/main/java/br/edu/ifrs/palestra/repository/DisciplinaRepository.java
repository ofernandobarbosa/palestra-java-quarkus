package br.edu.ifrs.palestra.repository;

import br.edu.ifrs.palestra.model.Disciplina;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplinaRepository implements PanacheRepository<Disciplina> {

        public Disciplina saveAndReturn(Disciplina disciplina) {
        persist(disciplina);
        getEntityManager().flush();
        return disciplina;
    }
}
