package com.cloudbees.examples.todo.api.service;

import java.util.List;

import com.cloudbees.examples.todo.api.model.ToDo;

/**
 * To do service
 * 
 * @author apemberton
 * 
 */
public interface ToDoService {
	public List<ToDo> find();

	public ToDo get(long id);

	public ToDo create(ToDo todo);

	public void delete(long id);
}
