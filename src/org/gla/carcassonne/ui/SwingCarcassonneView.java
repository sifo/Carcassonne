package org.gla.carcassonne.ui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.events.FirstCardPickedEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.RemainingTileEvent;

public class SwingCarcassonneView extends CarcassonneView
	implements ActionListener {

	private JFrameCarcassonne jframe;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 800;
	private final static int HEIGHT = 700;

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

	public void firstCardPicked(FirstCardPickedEvent event) {
		jframe.getJPanelBoard().addTileOnBoard(event.getNewTile());
	}

	public void remainingTile(RemainingTileEvent event) {
		jframe.getRemainingTileNumber().setText(event.getNewText());
	}

	public void nextTile(NextTileEvent event) {
		jframe.getJPanelTile().setTile(event.getNewTile());
	}

	public void actionPerformed(ActionEvent arg) {

	}
}