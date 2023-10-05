package br.edu.ifrs.palestra.service;

import java.time.Year;
import java.util.List;
import java.util.Random;

import org.hibernate.exception.ConstraintViolationException;

import br.edu.ifrs.palestra.dto.AlunoDTO;
import br.edu.ifrs.palestra.model.Aluno;
import br.edu.ifrs.palestra.repository.AlunoRepository;
import br.edu.ifrs.palestra.service.exceptions.DatabaseException;
import br.edu.ifrs.palestra.service.exceptions.NotFoundException;
import br.edu.ifrs.palestra.service.interfaces.AlunoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@ApplicationScoped
public class AlunoServiceImpl implements AlunoService {

    @Inject
    private AlunoRepository alunoRepository;

    private final Random random = new Random();

    @Override
    public List<Aluno> getAll() {
        return alunoRepository.findAll().list();
    }

    @Override
    public Aluno getById(Long id) {
        return alunoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado.", id));
    }

    @Override
    public Aluno save(AlunoDTO alunoDto) {
        Aluno aluno = new Aluno();
        aluno.nome = alunoDto.nome();
        aluno.sobrenome = alunoDto.sobrenome();

        String matricula = generateMatricula();
        while (alunoRepository.existsByMatricula(matricula)) {
            matricula = generateMatricula();
        }

        aluno.matricula = matricula;
        aluno.email = generateEmail(alunoDto.nome(), alunoDto.sobrenome());
        
        alunoRepository.persist(aluno);
        return aluno;
    }

    // Sobrecarga do método de salvar aluno.
    @Override
    public Aluno save(Aluno aluno) {
        alunoRepository.persist(aluno);
        return aluno;
    }

    @Override
    public Aluno update(Long id, AlunoDTO alunoDto) {
        try {
            Aluno aluno = getById(id);
            aluno.nome = alunoDto.nome();
            aluno.sobrenome = alunoDto.sobrenome();
            aluno.email = generateEmail(alunoDto.nome(), alunoDto.sobrenome());
            alunoRepository.persist(aluno);
            return aluno;
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(id);
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            if (alunoRepository.deleteById(id)) return true;
            else throw new NotFoundException(id);
        }catch(NotFoundException e){
            throw new NotFoundException(id);
        }catch(ConstraintViolationException e){
            throw new DatabaseException(e.getMessage());
        }

    }

    private String generateMatricula() {
        return String.format("%d%06d", Year.now().getValue(), random.nextInt(1000000));
    }

    private String generateEmail(String nome, String sobrenome) {
        return String.format("%s.%s@aluno.riogrande.ifrs.edu.br", nome.toLowerCase(), sobrenome.toLowerCase());
    }
}
