package br.com.ifsp.batalhanaval.gameobjects;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;

public class Part{

	int idShip;
	boolean isDestruct = false;
	
	
	public Part(int idShip) {
		this.idShip = idShip;
	}
	
	public boolean isPartDestruct() {
		return isDestruct;
	}
	

	
	
}
