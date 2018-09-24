package de.nordakademie.wpk.todolist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.todolist.application.constants.Constants;
import de.nordakademie.wpk.todolist.core.api.domain.Todo;
import de.nordakademie.wpk.todolist.core.api.service.ITodoService;
import de.nordakademie.wpk.todolist.ui.editor.TodoEditorInput;

public class NewTodoHandler {

	@Inject
	private ITodoService service;

	@Inject
	private IEventBroker messageBroker;

	@Execute
	public void execute() {
		System.out.println("TEST");
		Todo newTodo = new Todo("<title>", "<description>", 5, "<assignee>", false, null);
		service.save(newTodo);
		messageBroker.post(Constants.DATA_CHANGE, new TodoEditorInput(newTodo));
	}

}
