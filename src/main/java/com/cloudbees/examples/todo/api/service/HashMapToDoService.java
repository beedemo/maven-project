package com.cloudbees.examples.todo.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.joda.time.Instant;

import com.cloudbees.examples.todo.api.model.ToDo;

/**
 * To Do Service that uses an obviously non-thread safe hash map for
 * demonstration purposes.
 * 
 * @author apemberton
 * 
 */
@Named
public class HashMapToDoService implements ToDoService {

	private Map<Long, ToDo> todos;

	private long last;

	@PostConstruct
	private void init() {
		todos = new HashMap<Long, ToDo>();
	}

	@Override
	public List<ToDo> find() {
		return new ArrayList<ToDo>(todos.values());
	}

	@Override
	public ToDo get(long id) {
		return todos.get(Long.valueOf(id));
	}

	@Override
	public ToDo create(ToDo todo) {
		todo.setId(last++);
		todo.setCreatedDate(Instant.now());
		todos.put(todo.getId(), todo);

		return todo;
	}

	@Override
	public void delete(long id) {
		todos.remove(Long.valueOf(id));
	}

}
