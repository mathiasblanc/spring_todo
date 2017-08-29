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

	public Todo findByName(String name) {
		return repository.findByName(name);
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

	public List<Todo> findAll() {
		return (List<Todo>) repository.findAll();
	}

	public boolean exists(Todo todo) {
		if (todo.getId() == null && todo.getName() != null)
			return findByName(todo.getName()) != null;

		return false;
	}

}
