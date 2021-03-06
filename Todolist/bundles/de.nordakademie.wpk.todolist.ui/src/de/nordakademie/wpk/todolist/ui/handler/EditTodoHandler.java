
package de.nordakademie.wpk.todolist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import de.nordakademie.wpk.todolist.application.handler.AbstractEditorHandler;
import de.nordakademie.wpk.todolist.core.api.domain.Todo;
import de.nordakademie.wpk.todolist.ui.Activator;
import de.nordakademie.wpk.todolist.ui.editor.TodoEditorInput;

public class EditTodoHandler extends AbstractEditorHandler {
	
	@Inject
	private ESelectionService selectionService;

	@Execute
	public void execute() {
		Todo selection = (Todo) selectionService.getSelection();
		openEditor( new TodoEditorInput((Todo) selectionService.getSelection()), Activator.PLUGIN_ID);
	}

	@CanExecute
	public boolean canExecute() {
		Object selection = selectionService.getSelection();
		return selection instanceof Todo;
	}

}