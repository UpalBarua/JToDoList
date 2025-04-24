package dev.upal.jtodolist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class TaskList {
	private final JPanel listContainer;
	private final JScrollPane scrollPane;
	private final TaskManager taskManager;

	private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 18);
	private static final Font DETAIL_FONT = UIConstants.PRIMARY_FONT;
	private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
	private static final Color TASK_BACKGROUND = Color.WHITE;
	private static final Color BORDER_COLOR = new Color(220, 220, 220);

	public TaskList(TaskManager taskManager) {
		this.taskManager = taskManager;

		listContainer = new JPanel();
		listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
		listContainer.setBackground(BACKGROUND_COLOR);
		listContainer.setBorder(new EmptyBorder(30, 40, 30, 40));

		scrollPane = new JScrollPane(listContainer);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void refreshTasks() {
		listContainer.removeAll();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		for (Task task : taskManager.getAllTasks()) {
			JPanel taskPanel = createTaskPanel(task, dateFormat);
			listContainer.add(taskPanel);
			listContainer.add(Box.createRigidArea(new Dimension(0, 16)));
		}

		listContainer.revalidate();
		listContainer.repaint();
	}

	private JPanel createTaskPanel(Task task, SimpleDateFormat dateFormat) {
		JPanel taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		taskPanel.setBackground(TASK_BACKGROUND);
		taskPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		taskPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
				new EmptyBorder(15, 15, 15, 15)));

		JCheckBox titleBox = new JCheckBox(task.getTitle());
		titleBox.setFont(TITLE_FONT);
		titleBox.setSelected(task.isCompleted());
		titleBox.setBackground(TASK_BACKGROUND);

		JLabel descLabel = new JLabel("Description: " + task.getDescription());
		descLabel.setFont(DETAIL_FONT);

		JLabel dueLabel = new JLabel("Due Date: " + dateFormat.format(task.getDueDate()));
		dueLabel.setFont(DETAIL_FONT);

		JLabel priorityLabel = createPriorityLabel(task);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(new Color(255, 69, 58));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(DETAIL_FONT);
		deleteButton.setFocusPainted(false);
		deleteButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(6, 12, 6, 12)));

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taskManager.deleteTask(task);
				refreshTasks();
			}
		});

		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.X_AXIS));
		deletePanel.setBackground(TASK_BACKGROUND);
		deletePanel.add(Box.createHorizontalGlue());
		deletePanel.add(deleteButton);

		taskPanel.add(titleBox);
		taskPanel.add(Box.createRigidArea(new Dimension(0, 4)));
		taskPanel.add(descLabel);
		taskPanel.add(Box.createRigidArea(new Dimension(0, 4)));
		taskPanel.add(dueLabel);
		taskPanel.add(Box.createRigidArea(new Dimension(0, 4)));
		taskPanel.add(priorityLabel);
		taskPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		taskPanel.add(deletePanel);

		return taskPanel;
	}

	private JLabel createPriorityLabel(Task task) {
		JLabel priorityLabel = new JLabel("Priority: " + task.getPriority().name());
		priorityLabel.setFont(DETAIL_FONT);

		switch (task.getPriority()) {
		case High -> priorityLabel.setForeground(new Color(220, 53, 69));
		case Medium -> priorityLabel.setForeground(new Color(255, 193, 7));
		case Low -> priorityLabel.setForeground(new Color(40, 167, 69));
		}
		return priorityLabel;
	}
}
