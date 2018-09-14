package de.nordakademie.wpk.todolist.ui.editor;

import de.nordakademie.wpk.todolist.application.editor.EditorInput;
import de.nordakademie.wpk.todolist.ui.domain.Todo;

public class TodoEditorInput implements EditorInput<Todo> {

	private final Todo todo;

	public TodoEditorInput(Todo todo) {
		super();
		this.todo = todo;
	}

	@Override
	public String getId() {
		return todo.getTitle();
	}

	@Override
	public String getLabel() {
		return todo.getTitle();
	}

	@Override
	public String getTooltip() {
		return todo.getDescription();
	}

	@Override
	public Class<?> getEditorClass() {
		return TodoEditor.class;
	}

	@Override
	public String getResourceURIString() {
		return String.format("todo:%s", todo.getTitle());
	}

	@Override
	public String getIconURIString() {
		return null;
	}

	public Todo getInputObject() {
		return todo;
	}

}
