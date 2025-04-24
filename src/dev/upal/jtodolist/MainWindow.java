package dev.upal.jtodolist;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow {
	private final JFrame mainFrame;
	private final TaskManager taskManager = new TaskManager();
	private final TaskList taskList = new TaskList(taskManager);

	public MainWindow() {
		mainFrame = createMainFrame();
		mainFrame.add(new HeaderPanel(taskList, taskManager).getPanel(), BorderLayout.NORTH);
		mainFrame.add(taskList.getScrollPane(), BorderLayout.CENTER);
	}

	private JFrame createMainFrame() {
		JFrame frame = new JFrame("Java To-Do List");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		return frame;

	}

	public void show() {
		mainFrame.setVisible(true);
	}

}
