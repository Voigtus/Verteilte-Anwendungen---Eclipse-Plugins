package de.nordakademie.wpk.todolist.ui.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.nordakademie.wpk.todolist.core.api.domain.Todo;

public class TodoTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Todo todo = (Todo) element;
		if(columnIndex == 0) {
			return todo.getTitle();
		} else if(columnIndex == 1) {
			return todo.getDescription();
		} else if(columnIndex == 2) {
			return String.valueOf(todo.getPrioritaet());
		} else if(columnIndex == 3) {
			return todo.getAssignee();
		} else if(columnIndex == 4) {
			return String.valueOf(todo.isDone());
		}
		return "unkown";
	}

}
