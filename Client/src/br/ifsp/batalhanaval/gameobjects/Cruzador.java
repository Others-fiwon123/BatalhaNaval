package br.ifsp.batalhanaval.gameobjects;

public class Cruzador extends Ship {

	public Cruzador() {
		size = 3;
		id = 3;
		isHorizontal = true;
		parts = new Part[size];
		for (int i = 0; i < size; i++) {
			parts[i] = new Part(id);
		}
	}
}
