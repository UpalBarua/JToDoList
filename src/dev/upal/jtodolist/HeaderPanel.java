package dev.upal.jtodolist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HeaderPanel {
	private JPanel taskFormPanel;
	private final TaskList taskList;
	private final TaskManager taskManager;
	private boolean isFormVisible = false;

	public HeaderPanel(TaskList taskList, TaskManager taskManager) {
		this.taskList = taskList;
		this.taskManager = taskManager;
	}

	public JPanel getPanel() {
		JPanel newTaskPanel = new JPanel(new BorderLayout());
		newTaskPanel.setBackground(Color.WHITE);
		newTaskPanel.setBorder(new EmptyBorder(30, 40, 20, 40));

		taskFormPanel = new TaskFormPanel(taskList, taskManager).getPanel();
		taskFormPanel.setVisible(false);

		newTaskPanel.add(createHeadingLabel(), BorderLayout.WEST);
		newTaskPanel.add(createAddTaskButton(), BorderLayout.EAST);
		newTaskPanel.add(taskFormPanel, BorderLayout.SOUTH);

		return newTaskPanel;
	}

	private JLabel createHeadingLabel() {
		JLabel headingLabel = new JLabel("To-Do List");
		headingLabel.setFont(new Font("Noto Serif", Font.BOLD, 28));
		headingLabel.setForeground(Color.DARK_GRAY);
		return headingLabel;
	}

	private JButton createAddTaskButton() {
		JButton addTaskButton = new JButton("Add Task");

		addTaskButton.setFont(UIConstants.PRIMARY_FONT);
		addTaskButton.setFocusPainted(false);

		addTaskButton.addActionListener(e -> {
			isFormVisible = !isFormVisible;
			taskFormPanel.setVisible(isFormVisible);
			addTaskButton.setText(isFormVisible ? "Cancel" : "Add Task");
		});

		return addTaskButton;
	}
}
