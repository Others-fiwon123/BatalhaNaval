package br.com.ifsp.batalhanaval.gameobjects;

public class PortaAviao extends Ship{

	public PortaAviao() {
		size = 5;
		id = 1;
		isHorizontal = true;
		parts = new Part[size];
		for(int i = 0; i < size; i++) {
			parts[i] = new Part(id);
		}
	}
}
