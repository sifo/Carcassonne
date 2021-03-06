package org.gla.carcassonne.ui.events;

import java.awt.event.MouseEvent;

import org.gla.carcassonne.View;

public class MouseListenerEvent implements java.awt.event.MouseListener {
	private int x;
	private int y;
	private View view;

	public MouseListenerEvent(int x, int y, View view) {
		this.x = x;
		this.y = y;
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		view.getController().notifyPlacePiece(x, y, arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
