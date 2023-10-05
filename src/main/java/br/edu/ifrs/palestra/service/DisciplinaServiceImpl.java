package br.edu.ifrs.palestra.service;

import java.util.List;

import br.edu.ifrs.palestra.dto.DisciplinaDTO;
import br.edu.ifrs.palestra.model.Disciplina;
import br.edu.ifrs.palestra.repository.DisciplinaRepository;
import br.edu.ifrs.palestra.service.exceptions.DatabaseException;
import br.edu.ifrs.palestra.service.exceptions.NotFoundException;
import br.edu.ifrs.palestra.service.interfaces.DisciplinaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DisciplinaServiceImpl implements DisciplinaService {

    @Inject
    private DisciplinaRepository disciplinaRepository;

    @Override
    public List<Disciplina> getAll() {
        return disciplinaRepository.findAll().list();
    }

    @Override
    public Disciplina getById(Long id) {
        return disciplinaRepository.findByIdOptional((long) id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada no banco de dados.", id));
    }

    @Override
    public Disciplina save(DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = new Disciplina();
        disciplina.nome = disciplinaDto.nome();
        disciplina.semestre = disciplinaDto.semestre();
        disciplinaRepository.persist(disciplina);
        return disciplina;
    }

    @Override
    public Disciplina update(Long id, DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = getById(id);
        disciplina.nome = disciplinaDto.nome();
        disciplina.semestre = disciplinaDto.semestre();
        disciplinaRepository.persist(disciplina);
        return disciplina;
    }

    @Override
    public boolean delete(Long id) {
        try {
            if (disciplinaRepository.findByIdOptional((long) id).isPresent()) {
                return disciplinaRepository.deleteById((long) id);
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new DatabaseException(e.getMessage());
        }

    }

}