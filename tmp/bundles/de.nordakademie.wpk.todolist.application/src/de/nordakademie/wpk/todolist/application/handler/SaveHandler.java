package de.nordakademie.wpk.todolist.application.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService; 

public class SaveHandler {

	@Inject
	private EPartService partService;

	@Execute
	public void exec(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		partService.savePart(part, true);
	}

	@CanExecute
	public boolean canExec(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		return (part == null) ? false : part.isDirty();
	}
	
}
