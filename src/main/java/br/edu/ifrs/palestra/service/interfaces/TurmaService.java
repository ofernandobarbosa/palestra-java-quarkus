package br.edu.ifrs.palestra.service.interfaces;

import br.edu.ifrs.palestra.dto.TurmaDTO;
import br.edu.ifrs.palestra.model.Turma;

public interface TurmaService extends CrudService<Turma, TurmaDTO> {
    Turma matricular(Long turma_id, Long aluno_id);

    Turma cancelarMatricula(Long turma_id, Long aluno_id);
}
