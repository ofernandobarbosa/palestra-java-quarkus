package br.edu.ifrs.palestra.service.interfaces;

import br.edu.ifrs.palestra.dto.AlunoDTO;
import br.edu.ifrs.palestra.model.Aluno;

public interface AlunoService extends CrudService<Aluno, AlunoDTO> {
    Aluno save(Aluno aluno);
}
