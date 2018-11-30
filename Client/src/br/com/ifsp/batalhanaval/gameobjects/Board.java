package br.com.ifsp.batalhanaval.gameobjects;

public class Board {

	private Tile[][] matrix;
	
	public Board(int x, int y) {
		matrix = new Tile[x][y];
	}
	
	public Tile[][] getMatrix() {
		return matrix;
	}
}
