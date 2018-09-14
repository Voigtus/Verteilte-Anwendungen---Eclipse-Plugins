package de.nordakademie.wpk.todolist.ui.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.nordakademie.wpk.todolist.ui.provider.TodoTableLabelProvider;
import de.nordakademie.wpk.todolist.ui.service.TodoService;

public class TodoView {
	private static final String CONTEXT_MENU_ID = "de.nordakademie.wpk.todolist.ui.view.TodoView";

	private Table tblTodos;

	private TableViewer tableViewerTodos;

	@Inject
	private TodoService todoService;

	@Inject
	private EMenuService eMenuService;

	@Inject
	private ESelectionService selectionService;

	public TodoView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout(new GridLayout(1, false));

		tableViewerTodos = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		tblTodos = tableViewerTodos.getTable();
		tblTodos.setLinesVisible(true);
		tblTodos.setHeaderVisible(true);
		GridData gd_tblTodos = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tblTodos.widthHint = 351;
		tblTodos.setLayoutData(gd_tblTodos);

		TableColumn tblclmnName = new TableColumn(tblTodos, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");

		TableColumn tblclmnDescription = new TableColumn(tblTodos, SWT.NONE);
		tblclmnDescription.setWidth(100);
		tblclmnDescription.setText("Description");

		TableColumn tblclmnPriority = new TableColumn(tblTodos, SWT.NONE);
		tblclmnPriority.setWidth(100);
		tblclmnPriority.setText("Priority");

		TableColumn tblclmnEditor = new TableColumn(tblTodos, SWT.NONE);
		tblclmnEditor.setWidth(100);
		tblclmnEditor.setText("Editor");

		TableColumn tblclmnDone = new TableColumn(tblTodos, SWT.NONE);
		tblclmnDone.setWidth(54);
		tblclmnDone.setText("Done");

		initialiseViewer();
		initialiseContextMenu();
		initialiseSelectionHandling();
	}

	private void initialiseSelectionHandling() {
		tableViewerTodos.addSelectionChangedListener((event) -> {
			IStructuredSelection selection = event.getStructuredSelection();
			selectionService.setSelection(selection.getFirstElement());
		});
	}

	private void initialiseContextMenu() {
		eMenuService.registerContextMenu(tableViewerTodos.getTable(), String.format("popup:%s", CONTEXT_MENU_ID));
	}

	private void initialiseViewer() {
		tableViewerTodos.setContentProvider(new ArrayContentProvider());
		tableViewerTodos.setLabelProvider(new TodoTableLabelProvider());
		tableViewerTodos.setInput(todoService.loadAll());
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		tableViewerTodos.getTable().setFocus();
	}
}
