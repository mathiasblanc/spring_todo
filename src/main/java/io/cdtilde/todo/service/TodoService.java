package io.cdtilde.todo.service;

import java.util.List;

import io.cdtilde.todo.model.Todo;

public interface TodoService {

	Todo findById(Long id);

	Todo findByName(String name);

	void save(Todo todo);

	void update(Todo todo);

	void delete(Long id);

	List<Todo> findAll();

	boolean exists(Todo todo);

}
