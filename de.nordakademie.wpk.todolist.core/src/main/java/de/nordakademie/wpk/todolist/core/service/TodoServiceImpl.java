package de.nordakademie.wpk.todolist.core.service;

import java.util.HashSet;
import java.util.Set;

import de.nordakademie.wpk.todolist.core.api.domain.Todo;
import de.nordakademie.wpk.todolist.core.api.service.ITodoService;

public class TodoServiceImpl implements ITodoService {

	private static Set<Todo> todos = new HashSet<>();

	static {
		// String title, String description, int priorität, String editor, boolean done
		todos.add(new Todo("Bier kaufen", "Obacht: Bier ist alle! Absoluter Notstand", 1, "Nils", false, null));
		todos.add(new Todo("MEHR Bier saufen", "Notstand", 1, "Felix", true, null));
		todos.add(new Todo("Bier kaufen...", "Obacht!", 1, "Nils", false, null));

	}

	@Override
	public Set<Todo> loadAll() {
		return todos;
	}

	@Override
	public Todo load(String todoName) {
		return todos.stream()
				.filter(todo -> todoName.equals(todo.getTitle()))
				.findFirst()
				.orElseGet(null);
	}

	@Override
	public void save(Todo todo) {
		if (todos.contains(todo)) {
			todos.remove(todo);
		}
		todos.add(todo);
	}
}
