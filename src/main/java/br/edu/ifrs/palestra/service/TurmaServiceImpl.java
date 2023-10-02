package br.edu.ifrs.palestra.service;

import java.util.List;

import br.edu.ifrs.palestra.dto.TurmaDTO;
import br.edu.ifrs.palestra.model.Aluno;
import br.edu.ifrs.palestra.model.Disciplina;
import br.edu.ifrs.palestra.model.Turma;
import br.edu.ifrs.palestra.repository.TurmaRepository;
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
    public Turma getById(int id) {
        return turmaRepository.findByIdOptional((long) id)
                .orElseThrow(() -> new ServiceException("Turma não encontrada."));
    }

    @Override
    public Turma save(TurmaDTO turmaDto) {
        Turma turma = new Turma();
        Disciplina disciplina = disciplinaService.getById(turmaDto.disciplina_id());

        turma.setNome(turmaDto.nome());
        turma.setDisciplina(disciplina);

        return turmaRepository.saveAndReturn(turma);
    }

    @Override
    public Turma update(int id, TurmaDTO turmaDto) {
        Turma turma = getById(id);
        Disciplina disciplina = disciplinaService.getById(turmaDto.disciplina_id());

        turma.setNome(turmaDto.nome());
        turma.setDisciplina(disciplina);

        return turmaRepository.saveAndReturn(turma);
    }

    @Override
    public boolean delete(int id) {
        if(turmaRepository.findByIdOptional((long) id).isPresent()){
            return turmaRepository.deleteById((long)id);
        }
        return false;
    }

    public Turma matricular(int turma_id, int aluno_id) {
        Aluno aluno = alunoService.getById(aluno_id);
        Turma turma = getById(turma_id);

        if (turma.getAlunos().contains(aluno)) {
            throw new IllegalArgumentException("Aluno já está inserido na turma.");
        }

        turma.getAlunos().add(aluno);
        aluno.getTurmas().add(turma);
        turmaRepository.saveAndReturn(turma);
        alunoService.save(aluno);
        return turma;
    }

    public Turma cancelarMatricula(int turma_id, int aluno_id) {
        Aluno aluno = alunoService.getById(aluno_id);
        Turma turma = getById(turma_id);

        if (!turma.getAlunos().contains(aluno)) {
            throw new IllegalArgumentException("Aluno não pertenca a turma.");
        }

        turma.getAlunos().remove(aluno);
        aluno.getTurmas().remove(turma);
        turmaRepository.saveAndReturn(turma);
        alunoService.save(aluno);
        return turma;
    }

}
