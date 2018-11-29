package br.com.ifsp.batalhanaval.gameobjects;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import br.com.ifsp.batalhanaval.manager.GameManager;
import br.com.ifsp.batalhanaval.screen.GameBoard;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{

	Part part;
	
	public Tile(Part part){
		this.part = part;
		
		this.setWidth(30);
		this.setHeight(30);
		this.setFill(Color.ALICEBLUE);
		
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
	
	public Part getPart() {
		return part;
	}
}
