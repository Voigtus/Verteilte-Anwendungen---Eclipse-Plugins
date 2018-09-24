
package de.nordakademie.wpk.todolist.ui.editor;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.nordakademie.wpk.todolist.application.constants.Constants;
import de.nordakademie.wpk.todolist.application.handler.DirtyHandler;
import de.nordakademie.wpk.todolist.core.api.domain.Todo;
import de.nordakademie.wpk.todolist.core.api.service.ITodoService;
import de.nordakademie.wpk.todolist.ui.job.SaveTodoJob;

public class TodoEditor {

	private FormToolkit toolkit = new FormToolkit(Display.getDefault());
	private Text textDescription;
	private Spinner spinnerPriority;
	private Text textAssignee;
	private Text textName;

	private TodoEditorInput editorInput;
	private DirtyHandler dirtyHandler;
	
	@Inject
	private IEventBroker eventBroker;
	
	@Inject
	private ITodoService todoService;

	@Inject
	private MPart editorPart;

	@Inject
	public TodoEditor() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {

		Section sectionDetails = toolkit.createSection(parent, Section.EXPANDED | Section.TITLE_BAR);
		toolkit.paintBordersFor(sectionDetails);
		sectionDetails.setText("Edit Todo");

		Composite composite = toolkit.createComposite(sectionDetails, SWT.NONE);
		toolkit.paintBordersFor(composite);
		sectionDetails.setClient(composite);
		composite.setLayout(new GridLayout(2, false));

		Label labelName = toolkit.createLabel(composite, "Name: ", SWT.NONE);

		textName = toolkit.createText(composite, "", SWT.NONE);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label labelDescription = toolkit.createLabel(composite, "Description: ", SWT.NONE);

		textDescription = toolkit.createText(composite, "", SWT.NONE);
		textDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label labelPriority = toolkit.createLabel(composite, "Priority: ", SWT.NONE);

		spinnerPriority = new Spinner(composite, SWT.BORDER);
		spinnerPriority.setMinimum(1);
		spinnerPriority.setMaximum(5);
		spinnerPriority.setSelection(5);
		toolkit.adapt(spinnerPriority);
		toolkit.paintBordersFor(spinnerPriority);

		Label labelAssignee = toolkit.createLabel(composite, "Assignee: ", SWT.NONE);

		textAssignee = toolkit.createText(composite, "", SWT.NONE);
		textAssignee.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		dirtyHandler = new DirtyHandler(editorPart);

		initialiseDirtyHandling();
		initialiseInput();
		updateWidgets();
	}

	private void initialiseDirtyHandling() {
		textDescription.addModifyListener(dirtyHandler);
		textName.addModifyListener(dirtyHandler);
	}

	private void initialiseInput() {
		String resourceUriString = editorPart.getPersistedState().get(Constants.RESOURCE_URI_KEY);
		String toName = resourceUriString.replaceFirst("todo:", "");

		editorInput = new TodoEditorInput(todoService.load(toName));
	}

	private void updateWidgets() {
		Todo todo = editorInput.getInputObject();
		textName.setText(todo.getTitle());
		textDescription.setText(todo.getDescription());
		spinnerPriority.setSelection(todo.getPriorität());
		textAssignee.setText(todo.getAssignee());
	}

	@Persist
	public void save() {
		updateModel();
		updatePart();
		eventBroker.post(Constants.DATA_CHANGE, editorInput.getResourceURIString());
	}

	private void updateModel() {
		Todo inputObject = editorInput.getInputObject();
		inputObject.setTitle(textName.getText());
		inputObject.setDescription(textDescription.getText());
		inputObject.setAssignee(textAssignee.getText());
		
		SaveTodoJob saveTodoJob = new SaveTodoJob(inputObject, todoService);
		saveTodoJob.schedule();
		
		editorPart.setDirty(false);
	}

	private void updatePart() {
		editorPart.setElementId(editorInput.getId());
		editorPart.setLabel(editorInput.getLabel());
		editorPart.setTooltip(editorInput.getTooltip());
		editorPart.getPersistedState().put(Constants.RESOURCE_URI_KEY, editorInput.getResourceURIString());
	}
}