package dev.upal.jtodolist;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private List<Task> tasksList = new ArrayList<Task>();

	public void addTask(Task task) {
		tasksList.add(task);
	}

	public List<Task> getAllTasks() {
		return new ArrayList<Task>(tasksList);
	}

	public boolean deleteTask(Task task) {
		return tasksList.remove(task);
	}

}
