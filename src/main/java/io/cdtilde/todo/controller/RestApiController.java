package io.cdtilde.todo.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
		List<Todo> todos = todoService.findAll();

		if (todos.isEmpty())
			return new ResponseEntity<List<Todo>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTodo(@PathVariable("id") String id) {
		Todo todo = null;

		if (StringUtils.isNumeric(id))
			todo = todoService.findById(Long.valueOf(id));
		else
			todo = todoService.findByName(id);

		if (todo == null)
			return new ResponseEntity<CustomError>(new CustomError("Todo with id " + id + " not found"),
					HttpStatus.NOT_FOUND);

		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
}
