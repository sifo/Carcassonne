package org.gla.carcassonne.entities;

public class Zone {
	private Status status;
	private boolean hasPiece;

	public Zone(Status status) {
		this.status = status;
		this.hasPiece = false;
	}

	public Status getStatus() {
		return status;
	}

	public void setHasPiece(boolean hasPiece) {
		this.hasPiece = hasPiece;
	}

	public boolean hasPiece() {
		return hasPiece;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}
