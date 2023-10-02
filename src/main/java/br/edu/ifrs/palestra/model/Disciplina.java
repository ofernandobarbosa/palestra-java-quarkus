package br.edu.ifrs.palestra.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "disciplina")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Disciplina extends PanacheEntityBase{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer semestre;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnoreProperties("alunos")
    private List<Turma> turmas;

}
