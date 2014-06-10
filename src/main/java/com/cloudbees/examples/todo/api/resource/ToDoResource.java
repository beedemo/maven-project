package com.cloudbees.examples.todo.api.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cloudbees.examples.todo.api.model.ToDo;
import com.cloudbees.examples.todo.api.service.ToDoService;

/**
 * Get, create, and delete to do list items.
 * 
 * @author apemberton
 * 
 */
@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToDoResource {

	@Inject
	private ToDoService service;

	@GET
	public List<ToDo> getMany() {
		List<ToDo> todos = service.find();
		return todos;
	}

	@GET
	@Path("{id}")
	public ToDo getOne(@PathParam("id") long id) {
		return service.get(id);
	}

	@POST
	public ToDo createOne(@Valid ToDo todo) {
		ToDo created = service.create(todo);
		return created;
	}

	@DELETE
	@Path("{id}")
	public void deleteOne(@PathParam("id") long id) {
		service.delete(id);
	}

}
