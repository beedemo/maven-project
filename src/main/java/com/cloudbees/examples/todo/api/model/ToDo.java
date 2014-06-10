package com.cloudbees.examples.todo.api.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.joda.time.Instant;
import org.joda.time.LocalDate;

/**
 * A to do list item
 * 
 * @author apemberton
 * 
 */
public class ToDo {

	/**
	 * ID of the todo - generated
	 */
	private long id;

	/**
	 * Created date of the todo - generated
	 */
	private Instant createdDate;

	/**
	 * Description of the todo - required
	 */
	@NotNull
	private String description;

	/**
	 * Due date of the todo - date only
	 */
	@NotNull
	@Future
	private LocalDate dueDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
}
