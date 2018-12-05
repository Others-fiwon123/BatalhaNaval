package br.com.ifsp.batalhanaval.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{

	boolean isOpen;
	Part part;
	
	public Tile(Part part){
		this.part = part;
		this.isOpen = false;
		this.setWidth(30);
		this.setHeight(30);
		this.setFill(Color.ALICEBLUE);
		
	}
	
	public boolean getOpen() {
		return isOpen;
	}
	
	public void setOpen(boolean flag) {
		isOpen = flag;
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
	
	public Part getPart() {
		return part;
	}
}
