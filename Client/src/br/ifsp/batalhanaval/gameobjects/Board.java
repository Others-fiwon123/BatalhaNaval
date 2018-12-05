package br.ifsp.batalhanaval.gameobjects;

public class Board {

	int height, width;
	
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
