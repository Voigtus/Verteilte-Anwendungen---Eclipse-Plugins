package de.nordakademie.wpk.todolist.core.api.domain;

import java.io.Serializable;
import java.util.UUID;

public class Todo implements Serializable {
	private static final long serialVersionUID = 5007601441111965859L;
	private String title;
	private String description;
	private int prioritaet;
	private String assignee;
	private boolean done;
	private UUID id;

	public Todo(String title, String description, int priorität, String assignee, boolean done, UUID id) {
		super();
		this.title = title;
		this.description = description;
		this.prioritaet = priorität;
		this.assignee = assignee;
		this.done = done;
		this.id = (id == null? UUID.randomUUID() : id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrioritaet() {
		return prioritaet;
	}

	public void setPrioritaet(int priorität) {
		this.prioritaet = priorität;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String editor) {
		this.assignee = editor;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
