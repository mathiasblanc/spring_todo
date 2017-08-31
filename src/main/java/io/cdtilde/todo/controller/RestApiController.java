package io.cdtilde.todo.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.cdtilde.todo.model.Todo;
import io.cdtilde.todo.service.TodoService;
import io.cdtilde.todo.util.CustomError;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	private TodoService todoService;

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> listAllTodos() {
		List<Todo> todos = todoService.findAllTodos();

		if (todos.isEmpty())
			return new ResponseEntity<List<Todo>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/todo/done", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> listAllDone() {
		List<Todo> doneTodos = todoService.findAllDone();

		if (doneTodos.isEmpty())
			return new ResponseEntity<List<Todo>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Todo>>(doneTodos, HttpStatus.OK);
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTodo(@PathVariable("id") String id) {
		Todo todo = null;

		if (StringUtils.isNumeric(id))
			todo = todoService.findById(Long.valueOf(id));
		else
			todo = todoService.findByDescription(id);

		if (todo == null)
			return new ResponseEntity<CustomError>(new CustomError("Todo with id " + id + " not found"),
					HttpStatus.NOT_FOUND);

		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@RequestMapping(value = "/todo/", method = RequestMethod.POST)
	public ResponseEntity<?> createTodo(@RequestBody Todo todo, UriComponentsBuilder builder) {
		logger.info("Creating todo : {}", todo);

		todoService.save(todo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/todo/{id}").buildAndExpand(todo.getId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTodo(@PathVariable("id") long id) {
		logger.info("Deleting todo with id {}", id);

		Todo todo = todoService.findById(id);

		if (todo == null) {
			logger.error("Unable to delete. Todo with id {} not found.", id);
			return new ResponseEntity<CustomError>(
					new CustomError("Unable to delete. Todo with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}

		todoService.delete(id);

		return new ResponseEntity<Todo>(todo, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> setDone(@PathVariable("id") long id) {
		logger.info("Updating todo with id {}", id);

		Todo todo = todoService.findById(id);

		if (todo == null) {
			logger.error("Unable to update. Todo with id {} not found.", id);
			return new ResponseEntity<CustomError>(
					new CustomError("Unable to update. Todo with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}

		todo.setDoneAt(new Date());
		todoService.update(todo);

		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
}
