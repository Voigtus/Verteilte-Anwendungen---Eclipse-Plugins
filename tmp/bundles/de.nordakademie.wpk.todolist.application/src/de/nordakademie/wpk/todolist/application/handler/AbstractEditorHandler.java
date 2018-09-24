package de.nordakademie.wpk.todolist.application.handler;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import de.nordakademie.wpk.todolist.application.constants.Constants;
import de.nordakademie.wpk.todolist.application.editor.EditorInput;

public abstract class AbstractEditorHandler {

	private static final String EDITOR_STACK_ID = "de.nordakademie.wpk.todolist.ui.editors";
	
	@Inject
	private EPartService partService;
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;
	
	protected void openEditor(EditorInput<?> editorInput, String symbolicBundleName) {
		MPart part = partService.findPart(editorInput.getId());
		if (part == null) {
			// neu erstellen
			part = MBasicFactory.INSTANCE.createPart();
			part.setElementId(editorInput.getId());
			part.setLabel(editorInput.getLabel());
			part.setTooltip(editorInput.getTooltip());
			String contributionURI = String.format("bundleclass://%s/%s", symbolicBundleName,editorInput.getEditorClass().getName());
			part.setContributionURI(contributionURI);
			part.getPersistedState().put(Constants.RESOURCE_URI_KEY, editorInput.getResourceURIString());
			part.setIconURI(editorInput.getIconURIString());
			part.setCloseable(true);
			
			MPartStack stack = (MPartStack) modelService.find(EDITOR_STACK_ID, application);
			stack.getChildren().add(part);
		}
		partService.showPart(part, PartState.ACTIVATE);
	}
}
