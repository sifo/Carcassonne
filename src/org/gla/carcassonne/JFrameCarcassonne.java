package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneController;

public class JFrameCarcassonne extends CarcassonneView 
	implements ActionListener {

	private JFrame jframe;
	private JPanel jpanel;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 450;
	private final static int HEIGHT = 350;

	public JFrameCarcassonne(CarcassonneController controller) {
		super(controller);
		jframe = new JFrame(TITLE);
		jpanel = new JPanel();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(new FlowLayout());
		jframe.pack();
		jframe.setSize(WIDTH, HEIGHT);
	}

	public void close() {
		jframe.dispose();
	}

	public void display() {
		jframe.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg) {

	}

	public JFrame getJFrame() {
		return jframe;
	}

	public JPanel getJPanel() {
		return jpanel;
	}
}
