package br.com.crudTest.crudvueback.repository;

import br.com.crudTest.crudvueback.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
