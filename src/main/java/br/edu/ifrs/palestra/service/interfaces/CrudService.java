package br.edu.ifrs.palestra.service.interfaces;

import java.util.List;

public interface CrudService<T, DTO>{
    List<T> getAll();

    T getById(Long id);

    T save(DTO dto);

    T update(Long id, DTO dto);

    boolean delete(Long id);
}
