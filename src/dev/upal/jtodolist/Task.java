package dev.upal.jtodolist;

import java.util.Date;

public class Task {
	private final String title;
	private final String description;
	private final TaskPriority priority;
	private final Date dueDate;
	private final boolean isCompleted;

	public Task(String title, String description, TaskPriority priority, Date dueDate) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.dueDate = dueDate;
		this.isCompleted = false;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

}
