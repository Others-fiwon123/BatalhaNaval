package br.com.ifsp.batalhanaval.gameobjects;

public class Player {
	
	Ship[] ships;
	Board board;
	
	public Player(int i, int j) {
		ships = new Ship[4];
		ships[0] = new PortaAviao();
		ships[1] = new Encouracado();
		ships[2] = new Cruzador();
		ships[3] = new Submarino();
		
		board = new Board(i, j);
	}
	
	public Ship[] getShips() {
		return ships;
	}
	
}
