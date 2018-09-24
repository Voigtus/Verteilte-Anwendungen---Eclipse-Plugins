package de.nordakademie.wpk.todolist.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class QuitHandler {

	@Execute
	public void exec(IWorkbench workbench, Shell shell) {
		if (MessageDialog.openConfirm(shell, "Really wanna quit?", "You wanna quit?!")) {
			workbench.close();
		}
	}
}
