package br.edu.ifrs.palestra.service.interfaces;

import br.edu.ifrs.palestra.dto.TurmaDTO;
import br.edu.ifrs.palestra.model.Turma;

public interface TurmaService extends CrudService<Turma, TurmaDTO> {
    Turma matricular(int turma_id, int aluno_id);

    Turma cancelarMatricula(int turma_id, int aluno_id);
}
