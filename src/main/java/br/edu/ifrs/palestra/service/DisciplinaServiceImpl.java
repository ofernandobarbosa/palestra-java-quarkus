package br.edu.ifrs.palestra.service;

import java.util.List;

import br.edu.ifrs.palestra.dto.DisciplinaDTO;
import br.edu.ifrs.palestra.model.Disciplina;
import br.edu.ifrs.palestra.repository.DisciplinaRepository;
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
    public Disciplina getById(int id) {
        return disciplinaRepository.findByIdOptional((long) id)
                .orElseThrow(() -> new ServiceException("Disciplina não encontrada no banco de dados."));
    }

    @Override
    public Disciplina save(DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDto.nome());
        disciplina.setSemestre(disciplinaDto.semestre());
        return disciplinaRepository.saveAndReturn(disciplina);
    }

    @Override
    public Disciplina update(int id, DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = getById(id);
        disciplina.setNome(disciplinaDto.nome());
        disciplina.setSemestre(disciplinaDto.semestre());
        return disciplinaRepository.saveAndReturn(disciplina);
    }

    @Override
    public boolean delete(int id) {
        if(disciplinaRepository.findByIdOptional((long) id).isPresent()){
            return disciplinaRepository.deleteById((long) id);
        }
        return false;
    }

}