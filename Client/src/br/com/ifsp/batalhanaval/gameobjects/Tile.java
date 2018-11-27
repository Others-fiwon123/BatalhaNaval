package br.com.ifsp.batalhanaval.gameobjects;

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
		EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
		    public void handle(InputEvent event) {
		        ((Rectangle)view).setFill(Color.BLACK);
		        
		        if(part != null) {
		        	((Rectangle)view).setFill(Color.GREEN);
		        }
		    }
		};
		view.setOnMouseClicked(handler);
	}
	
	public Node getView() {
		return view;
	}
}
