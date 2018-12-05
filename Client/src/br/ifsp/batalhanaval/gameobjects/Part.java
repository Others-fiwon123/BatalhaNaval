package br.ifsp.batalhanaval.gameobjects;

public class Part {

	int idShip;
	boolean isDestruct = false;

	public Part(int idShip) {
		this.idShip = idShip;
	}

	public int getIdShip() {
		return idShip;
	}

	public boolean isPartDestruct() {
		return isDestruct;
	}

	public void destroyPart() {
		isDestruct = true;
	}

}
