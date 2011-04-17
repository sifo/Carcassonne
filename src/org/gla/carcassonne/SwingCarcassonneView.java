package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.JFrameCarcassonne;

public class SwingCarcassonneView extends CarcassonneView
	implements ActionListener {

	private JFrame jframe;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 550;
	private final static int HEIGHT = 450;

	public SwingCarcassonneView(CarcassonneController controller) {
		super(controller);
		jframe = new JFrameCarcassonne(TITLE);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.getContentPane().setLayout(new GridLayout(2, 2));
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
