package org.gla.carcassonne;

import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
		setJavaLookAndFeel();
		Model model = new Model();
		Controller controller = new Controller(model);
		controller.displayViews();
		model.start();
	}

	public static void setJavaLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting Java LAF: " + e);
		}
	}
}
