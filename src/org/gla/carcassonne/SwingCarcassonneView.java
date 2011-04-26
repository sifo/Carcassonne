package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.JFrameCarcassonne;

public class SwingCarcassonneView extends CarcassonneView
	implements ActionListener {

	private JFrame jframe;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 580;
	private final static int HEIGHT = 460;

	public SwingCarcassonneView(CarcassonneController controller) {
		super(controller);
		jframe = new JFrameCarcassonne(TITLE);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
	}

	public void close() {
		jframe.dispose();
	}

	public void display() {
		jframe.setVisible(true);
	}

	public JFrame getJFrame() {
		return jframe;
	}

	public void actionPerformed(ActionEvent arg) {

	}
}
