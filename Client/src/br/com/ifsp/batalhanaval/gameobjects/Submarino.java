package br.com.ifsp.batalhanaval.gameobjects;

public class Submarino extends Ship{

	public Submarino() {
		size = 2;
		id = 4;
		isHorizontal = true;
		parts = new Part[size];
		for(int i = 0; i < size; i++) {
			parts[i] = new Part(id);
		}
	}
}
