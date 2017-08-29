package io.cdtilde.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cdtilde.todo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	Todo findByName(String name);
}
