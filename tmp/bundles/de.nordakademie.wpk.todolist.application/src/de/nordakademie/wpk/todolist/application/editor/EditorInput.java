package de.nordakademie.wpk.todolist.application.editor;

public interface EditorInput<T> {

	String getId();

	String getLabel();

	String getTooltip();

	Class<?> getEditorClass();

	String getResourceURIString();

	String getIconURIString();
	
	T getInputObject();

}
