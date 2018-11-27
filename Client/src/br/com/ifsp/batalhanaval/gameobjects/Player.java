package br.com.ifsp.batalhanaval.gameobjects;

public class Player {
	
	Ship[] ships;
	
	public Player() {
		ships = new Ship[4];
		ships[0] = new PortaAviao();
		ships[1] = new Encouracado();
		ships[2] = new Cruzador();
		ships[3] = new Submarino();
	}
	
}
