package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.JPanelMain;
import org.gla.carcassonne.CarcassonneController;

public class JFrameCarcassonne extends CarcassonneView 
	implements ActionListener {

	private JFrame jframe;
	private JPanel jpanel;
	private Image imageInitial;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 450;
	private final static int HEIGHT = 350;

	public JFrameCarcassonne(CarcassonneController controller) {
		super(controller);
		jframe = new JFrame(TITLE);
		jpanel = new JPanelMain();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(new FlowLayout());
		jframe.getContentPane().add(jpanel);
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
