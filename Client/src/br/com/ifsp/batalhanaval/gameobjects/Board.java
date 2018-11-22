package br.com.ifsp.batalhanaval.gameobjects;

public class Board {

	private Ship[][] matrix;
	
	public Board(int i, int j) {
		
		matrix = new Ship[i][j];
		
	}
	
}
