package io.cdtilde.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cdtilde.todo.model.Todo;
import io.cdtilde.todo.repository.TodoRepository;

@Service("TodoService")
@Transactional
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository repository;

	public Todo findById(Long id) {
		return repository.findOne(id);
	}

	public Todo findByDescription(String description) {
		return repository.findByDescription(description);
	}

	public void save(Todo todo) {
		repository.save(todo);
	}

	public void update(Todo todo) {
		repository.save(todo);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public List<Todo> findAllTodos() {
		return repository.findAllTodos();
	}

	public boolean exists(Todo todo) {
		if (todo.getId() == null && todo.getDescription() != null)
			return findByDescription(todo.getDescription()) != null;

		return false;
	}

	@Override
	public List<Todo> findAllDone() {
		return repository.findAllDone();
	}

}
