package br.edu.ifrs.palestra.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "turma")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Turma extends PanacheEntityBase{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, unique = true, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    @JsonIgnoreProperties("turmas")
    private Disciplina disciplina;

    @ManyToMany
    @JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    @JsonIgnoreProperties("turmas")
    private List<Aluno> alunos;

}
