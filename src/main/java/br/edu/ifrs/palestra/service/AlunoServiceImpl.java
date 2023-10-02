package br.edu.ifrs.palestra.service;

import java.time.Year;
import java.util.List;
import java.util.Random;

import br.edu.ifrs.palestra.dto.AlunoDTO;
import br.edu.ifrs.palestra.model.Aluno;
import br.edu.ifrs.palestra.repository.AlunoRepository;
import br.edu.ifrs.palestra.service.interfaces.AlunoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
    public Aluno getById(int id) {
        return alunoRepository.findByIdOptional((long) id).orElseThrow(() -> new ServiceException("Aluno não encontrado."));
    }

    @Override
    public Aluno save(AlunoDTO alunoDto) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.nome());
        aluno.setSobrenome(alunoDto.sobrenome());

        String matricula = generateMatricula();
        while (alunoRepository.existsByMatricula(matricula)) {
            matricula = generateMatricula();
        }

        aluno.setMatricula(matricula);
        aluno.setEmail(generateEmail(alunoDto.nome(), alunoDto.sobrenome()));
        return alunoRepository.saveAndReturn(aluno);
    }

    // Sobrecarga do método de salvar aluno.
    @Override
    public Aluno save(Aluno aluno) {
        return alunoRepository.saveAndReturn(aluno);
    }

    @Override
    public Aluno update(int id, AlunoDTO alunoDto) {
        Aluno aluno = getById(id);
        aluno.setNome(alunoDto.nome());
        aluno.setSobrenome(alunoDto.sobrenome());
        aluno.setEmail(generateEmail(alunoDto.nome(), alunoDto.sobrenome()));
        return alunoRepository.saveAndReturn(aluno);
    }

    @Override
    public boolean delete(int id) {
        if(alunoRepository.findByIdOptional((long) id).isPresent()){
            return alunoRepository.deleteById((long) id);
        }
        return false;
    }

    private String generateMatricula() {
        return String.format("%d%06d", Year.now().getValue(), random.nextInt(1000000));
    }

    private String generateEmail(String nome, String sobrenome) {
        return String.format("%s.%s@aluno.riogrande.ifrs.edu.br", nome.toLowerCase(), sobrenome.toLowerCase());
    }
}
