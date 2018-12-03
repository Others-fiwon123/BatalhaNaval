package br.com.ifsp.batalhanaval.gameobjects;

public class Encouracado extends Ship{

	public Encouracado() {
		size = 4;
		id = 2;
		isHorizontal = true;
		parts = new Part[size];
		for(int i = 0; i < size; i++) {
			parts[i] = new Part(id);
		}
	}
}
