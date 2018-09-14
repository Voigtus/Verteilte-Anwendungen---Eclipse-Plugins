package de.nordakademie.wpk.todolist.ui.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.todolist.ui.domain.Todo;

@Creatable
public class TodoService {
	
	@Inject
	private IEventBroker msgBroker;
	
	private static Set<Todo> todos = new HashSet<>();

	static {
		// String title, String description, int priorität, String editor, boolean done
		todos.add(new Todo("Bier kaufen", "Obacht: Bier ist alle! Absoluter Notstand", 1, "Nils", false));
		todos.add(new Todo("MEHR Bier saufen", "Notstand", 1, "Felix", true));
		todos.add(new Todo("Bier kaufen...", "Obacht!", 1, "Nils", false));

	}

	public Set<Todo> loadAll() {
		return todos;
	}

	public Todo load(final String todoName) {
		return todos.stream().filter(todo -> todoName.equals(todo.getTitle())).findFirst().get();
	}
	
	public void save(Todo todo) {
		todos.add(todo);
	}
}
