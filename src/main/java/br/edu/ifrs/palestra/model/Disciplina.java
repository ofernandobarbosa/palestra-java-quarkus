package br.edu.ifrs.palestra.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina extends PanacheEntity{

    @Column(length = 255, unique = true, nullable = false)
    public String nome;

    @Column(nullable = false)
    public Long semestre;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnoreProperties("alunos")
    public List<Turma> turmas;

}
