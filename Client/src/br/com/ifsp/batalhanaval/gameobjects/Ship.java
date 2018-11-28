package br.com.ifsp.batalhanaval.gameobjects;

public abstract class Ship {

	int id;
	protected int size;
	Part[] parts;
	
	public Part[] getParts() {
		return parts;
	}
}
