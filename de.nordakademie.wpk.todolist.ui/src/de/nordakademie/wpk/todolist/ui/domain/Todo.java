package de.nordakademie.wpk.todolist.ui.domain;

public class Todo {

	private String title;
	private String description;
	private int priorität;
	private String assignee;
	private boolean done;

	public Todo(String title, String description, int priorität, String assignee, boolean done) {
		super();
		this.title = title;
		this.description = description;
		this.priorität = priorität;
		this.assignee = assignee;
		this.done = done;
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

	public int getPriorität() {
		return priorität;
	}

	public void setPriorität(int priorität) {
		this.priorität = priorität;
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
