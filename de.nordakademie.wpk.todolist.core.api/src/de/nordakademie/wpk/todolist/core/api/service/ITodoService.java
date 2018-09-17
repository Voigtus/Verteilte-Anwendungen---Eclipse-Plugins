package de.nordakademie.wpk.todolist.core.api.service;

import java.util.Set;

import de.nordakademie.wpk.todolist.core.api.domain.Todo;

public interface ITodoService {

	/**
	 * Loads all todos from persistance
	 * 
	 * @return
	 */
	Set<Todo> loadAll();

	/**
	 * Loads todo with name. null if none found
	 * 
	 * @param todoName
	 * @return
	 */
	Todo load(String todoName);

	/**
	 * Saves todo to persistance
	 * 
	 * @param todo
	 */
	void save(Todo todo);

}