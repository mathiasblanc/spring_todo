package io.cdtilde.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cdtilde.todo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	Todo findByDescription(String descrition);

	@Query("select t from Todo t where doneAt is null")
	List<Todo> findAllTodos();

	@Query("select t from Todo t where t.doneAt is not null")
	List<Todo> findAllDone();

}
