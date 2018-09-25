package de.nordakademie.wpk.todolist.ui.job;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.nordakademie.wpk.todolist.core.api.domain.Todo;
import de.nordakademie.wpk.todolist.core.api.service.ITodoService;

public class SaveTodoJob extends Job {

	private final static String name = "SAVE_TODO_JOB";

	private Todo todo;
	private ITodoService service;
	
	public SaveTodoJob(Todo todo, ITodoService service) {
		super(name);
		this.todo = todo;
		this.service = service;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		service.save(todo);
		return Status.OK_STATUS;
	}

}
