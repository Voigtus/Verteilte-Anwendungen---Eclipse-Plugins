package de.nordakademie.wpk.todolist.application.handler;

import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DirtyHandler extends SelectionAdapter implements ModifyListener {

	private final MDirtyable dirtyable; 
	
	public DirtyHandler(MDirtyable dirtyable) {
		super();
		this.dirtyable = dirtyable;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		dirtyable.setDirty(true);
	}
	
	@Override
	public void widgetSelected(SelectionEvent event) {
		dirtyable.setDirty(true);
	}

}
