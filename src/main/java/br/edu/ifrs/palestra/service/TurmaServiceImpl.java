package br.edu.ifrs.palestra.service;

import java.util.List;

import br.edu.ifrs.palestra.dto.TurmaDTO;
import br.edu.ifrs.palestra.model.Aluno;
import br.edu.ifrs.palestra.model.Disciplina;
import br.edu.ifrs.palestra.model.Turma;
import br.edu.ifrs.palestra.repository.TurmaRepository;
import br.edu.ifrs.palestra.service.exceptions.NotFoundException;
import br.edu.ifrs.palestra.service.interfaces.AlunoService;
import br.edu.ifrs.palestra.service.interfaces.DisciplinaService;
import br.edu.ifrs.palestra.service.interfaces.TurmaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TurmaServiceImpl implements TurmaService {

    @Inject
    private TurmaRepository turmaRepository;

    @Inject
    private DisciplinaService disciplinaService;

    @Inject
    private AlunoService alunoService;

    @Override
    public List<Turma> getAll() {
        return turmaRepository.findAll().list();
    }

    @Override
    public Turma getById(Long id) {
        return turmaRepository.findByIdOptional((long) id)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada.", id));
    }

    @Override
    public Turma save(TurmaDTO turmaDto) {

        Turma turma = new Turma();
        Disciplina disciplina = disciplinaService.getById(turmaDto.disciplina_id());

        turma.nome = turmaDto.nome();
        turma.disciplina = disciplina;

        return turmaRepository.saveAndReturn(turma);

    }

    @Override
    public Turma update(Long id, TurmaDTO turmaDto) {
        Turma turma = getById(id);
        Disciplina disciplina = disciplinaService.getById(turmaDto.disciplina_id());

        turma.nome = turmaDto.nome();
        turma.disciplina = disciplina;

        return turmaRepository.saveAndReturn(turma);
    }

    @Override
    public boolean delete(Long id) {
        if (turmaRepository.findByIdOptional((long) id).isPresent()) {
            return turmaRepository.deleteById((long) id);
        }
        return false;
    }

    public Turma matricular(Long turma_id, Long aluno_id) {
        Aluno aluno = alunoService.getById(aluno_id);
        Turma turma = getById(turma_id);

        if (turma.alunos.contains(aluno)) {
            throw new IllegalArgumentException("Aluno já está inserido na turma.");
        }

        turma.alunos.add(aluno);
        aluno.turmas.add(turma);
        turmaRepository.saveAndReturn(turma);
        alunoService.save(aluno);
        return turma;
    }

    public Turma cancelarMatricula(Long turma_id, Long aluno_id) {
        Aluno aluno = alunoService.getById(aluno_id);
        Turma turma = getById(turma_id);

        if (!turma.alunos.contains(aluno)) {
            throw new IllegalArgumentException("Aluno não pertenca a turma.");
        }

        turma.alunos.remove(aluno);
        aluno.turmas.remove(turma);
        turmaRepository.saveAndReturn(turma);
        alunoService.save(aluno);
        return turma;
    }

}
