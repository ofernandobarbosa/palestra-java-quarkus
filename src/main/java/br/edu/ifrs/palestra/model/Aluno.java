package br.edu.ifrs.palestra.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno extends PanacheEntity {

    @Column(length = 100, nullable = false)
    public String nome;

    @Column(length = 100, nullable = false)
    public String sobrenome;

    @Column(length = 10, unique = true)
    public String matricula;

    @Column(length = 255, unique = true)
    public String email;

    @ManyToMany(mappedBy = "alunos")
    @JsonIgnoreProperties("alunos")
    public List<Turma> turmas;

}
