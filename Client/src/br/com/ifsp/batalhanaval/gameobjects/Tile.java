package br.com.ifsp.batalhanaval.gameobjects;

import br.com.ifsp.batalhanaval.manager.GameManager;
import br.com.ifsp.batalhanaval.screen.GameBoard;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile{
	
	Node view;
	Part part;
	
	public Tile(Part part){
		this.part = part;
		
		view = new Rectangle(30, 30, Color.ALICEBLUE);
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
	
	public Node getView() {
		return view;
	}
	
	public Part getPart() {
		return part;
	}
}
