package br.com.ifsp.batalhanaval.gameobjects;

public class Cruzador extends Ship{

	Part[] parts;
	
	public Cruzador() {
		size = 3;
		id = 3;
		parts = new Part[size];
		for(int i = 0; i < size; i++) {
			parts[i] = new Part(id);
		}
	}
}
