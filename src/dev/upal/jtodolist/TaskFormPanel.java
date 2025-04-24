package dev.upal.jtodolist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class TaskFormPanel {
	private JTextField titleField;
	private JTextArea descriptionField;
	private JComboBox<TaskPriority> priorityField;
	private JSpinner dueDateSpinner;
	private final TaskManager taskManager;
	private final TaskList taskList;

	public TaskFormPanel(TaskList taskList, TaskManager taskManager) {
		this.taskList = taskList;
		this.taskManager = taskManager;
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

		panel.add(createTitleField());
		panel.add(Box.createVerticalStrut(15));
		panel.add(createDescriptionField());
		panel.add(Box.createVerticalStrut(15));
		panel.add(createDueDateField());
		panel.add(Box.createVerticalStrut(15));
		panel.add(createPriorityField());
		panel.add(Box.createVerticalStrut(20));
		panel.add(createFormButtonGroup());

		panel.setVisible(false);
		return panel;
	}

	private JPanel createTitleField() {
		JPanel titleFieldPanel = new JPanel(new BorderLayout());
		titleFieldPanel.setBackground(Color.WHITE);

		JLabel titleFieldLabel = new JLabel("Title");
		titleFieldLabel.setFont(UIConstants.PRIMARY_FONT);
		titleField = new JTextField();
		titleField.setFont(UIConstants.PRIMARY_FONT);

		titleFieldPanel.add(titleFieldLabel, BorderLayout.NORTH);
		titleFieldPanel.add(titleField, BorderLayout.CENTER);
		return titleFieldPanel;
	}

	private JPanel createDescriptionField() {
		JPanel descriptionFieldPanel = new JPanel(new BorderLayout());
		descriptionFieldPanel.setBackground(Color.WHITE);

		JLabel descriptionFieldLabel = new JLabel("Description");
		descriptionFieldLabel.setFont(UIConstants.PRIMARY_FONT);
		descriptionField = new JTextArea(5, 20);
		descriptionField.setFont(UIConstants.PRIMARY_FONT);
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);

		descriptionFieldPanel.add(descriptionFieldLabel, BorderLayout.NORTH);
		descriptionFieldPanel.add(new JScrollPane(descriptionField), BorderLayout.CENTER);
		return descriptionFieldPanel;
	}

	private JPanel createDueDateField() {
		JPanel dueDateFieldPanel = new JPanel(new BorderLayout());
		dueDateFieldPanel.setBackground(Color.WHITE);

		JLabel dueDateFieldLabel = new JLabel("Due Date");
		dueDateFieldLabel.setFont(UIConstants.PRIMARY_FONT);

		SpinnerDateModel model = new SpinnerDateModel();
		dueDateSpinner = new JSpinner(model);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(dueDateSpinner, "dd/MM/yyyy");
		dueDateSpinner.setEditor(editor);
		dueDateSpinner.setFont(UIConstants.PRIMARY_FONT);

		dueDateFieldPanel.add(dueDateFieldLabel, BorderLayout.NORTH);
		dueDateFieldPanel.add(dueDateSpinner, BorderLayout.CENTER);
		return dueDateFieldPanel;
	}

	private JPanel createPriorityField() {
		JPanel priorityFieldPanel = new JPanel(new BorderLayout());
		priorityFieldPanel.setBackground(Color.WHITE);

		JLabel priorityFieldLabel = new JLabel("Priority");
		priorityFieldLabel.setFont(UIConstants.PRIMARY_FONT);

		priorityField = new JComboBox<>(TaskPriority.values());
		priorityField.setSelectedIndex(2);
		priorityField.setFont(UIConstants.PRIMARY_FONT);

		priorityFieldPanel.add(priorityFieldLabel, BorderLayout.NORTH);
		priorityFieldPanel.add(priorityField, BorderLayout.CENTER);
		return priorityFieldPanel;
	}

	private JPanel createFormButtonGroup() {
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(Color.WHITE);

		JPanel formButtonGroupPanel = new JPanel();
		formButtonGroupPanel.setLayout(new BoxLayout(formButtonGroupPanel, BoxLayout.X_AXIS));
		formButtonGroupPanel.setBackground(Color.WHITE);

		JButton clearButton = new JButton("Clear");
		JButton addButton = new JButton("Add");

		clearButton.setFont(UIConstants.PRIMARY_FONT);
		addButton.setFont(UIConstants.PRIMARY_FONT);

		clearButton.addActionListener(e -> {
			titleField.setText("");
			descriptionField.setText("");
			priorityField.setSelectedIndex(2);
			dueDateSpinner.setValue(new Date());
		});

		addButton.addActionListener(e -> {
			String title = titleField.getText().trim();
			if (title.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter a title for the task.", "Missing Title",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			String description = descriptionField.getText();
			TaskPriority priority = (TaskPriority) priorityField.getSelectedItem();
			Date dueDate = (Date) dueDateSpinner.getValue();

			taskManager.addTask(new Task(title, description, priority, dueDate));
			taskList.refreshTasks();

			titleField.setText("");
			descriptionField.setText("");
			priorityField.setSelectedIndex(2);
			dueDateSpinner.setValue(new Date());
		});

		formButtonGroupPanel.add(clearButton);
		formButtonGroupPanel.add(Box.createHorizontalStrut(10));
		formButtonGroupPanel.add(addButton);

		wrapper.add(formButtonGroupPanel, BorderLayout.EAST);
		return wrapper;
	}
}
