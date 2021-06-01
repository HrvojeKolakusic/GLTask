package task.gui;

import task.algorithm.LogicTask;

public class GLMain {

	public static void main(String[] args) {
		GLMainWindow window = new GLMainWindow();
		String output = LogicTask.start();
		window.output.setText(output);
	}

}
