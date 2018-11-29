package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.ifsp.batalhanaval.gameobjects.Board;
import br.com.ifsp.batalhanaval.gameobjects.Tile;
import br.com.ifsp.batalhanaval.manager.GameManager;
import br.com.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameBoard
{
	
	@FXML 
	public Pane panePlayer;
	
	@FXML
	public Label lbPortaAviao,
		  lbEncouracado,
		  lbCruzador,
		  lbSubmarino;
	
	@FXML 
	public ImageView viewPortaAviao,
					 viewEncouracado,
					 viewCruzador,
					 viewSubmarino;
	
	@FXML 
	public GridPane gridPlayer, gridEnemy; 
	
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	
	ImageView hold;
	int holdSize;
	
	@FXML
	public void startMenu(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(ScreenManager.MENU));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void hit(int i, int j) {
		for(Node child : gridPlayer.getChildren()) {
			try {
				if(gridPlayer.getRowIndex(child) == i &&
				   gridPlayer.getColumnIndex(child) == j ) {
					((Rectangle)child).setFill(Color.BLACK);
				}
			}catch(Exception e) {
				
			}
		}
	}
	
	public void OnMouseClick(MouseEvent evt) {
		
		if(hold != null) {
			hold.setOpacity(0.25);
		}
		
		ImageView typeShip = (ImageView)evt.getSource();
		if(typeShip == viewPortaAviao) {
			holdSize = 5;
		}else if(typeShip == viewEncouracado) {
			holdSize = 4;
		}else if(typeShip == viewCruzador) {
			holdSize = 3;
		}else if(typeShip == viewSubmarino) {
			holdSize = 2;
		}
		
		hold = (ImageView)evt.getSource();
		hold.setOpacity(1);
	}
	
	/*@FXML
	public void OnMousePressed(MouseEvent evt) {
		
	       orgSceneX = evt.getSceneX();
           orgSceneY = evt.getSceneY();
           orgTranslateX = ((Node)(evt.getSource())).getTranslateX();
           orgTranslateY = ((Node)(evt.getSource())).getTranslateY();
	}
	
	@FXML
	public void OnMouseDragged(MouseEvent evt) {
		
        double offsetX = evt.getSceneX() - orgSceneX;
        double offsetY = evt.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;
         
        ((Node)(evt.getSource())).setTranslateX(newTranslateX);
        ((Node)(evt.getSource())).setTranslateY(newTranslateY);
	}

	@FXML
	public void OnMouseReleased(MouseEvent evt) {
		
        node.setFill(Color.RED);
	}*/
	
	
	
	@FXML
    public void initialize() throws IOException {
	   Board board = new Board(10, 10);
	   
	   gridPlayer.setPrefHeight(30);
	   gridPlayer.setPrefWidth(30);
	   
	   for(int i = 0; i < 10; i++) {
		   for(int j = 0; j < 10; j++) {
			   Tile tilePlayer = new Tile(null);
			   Tile tile = new Tile(null);

			   gridPlayer.add(tilePlayer.getView(), i, j, 1, 1);
			   gridEnemy.add(tile.getView(),i, j, 1, 1);
			   
			   EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
				    public void handle(InputEvent event) {
				       ((Rectangle)tile.getView()).setFill(Color.AQUA);
				       
				       int i = gridPlayer.getRowIndex(tile.getView());
				       int j = gridPlayer.getColumnIndex(tile.getView());
				       try {
						GameManager.getInstance().sendMessage(i, j);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				       
				       if(tile.getPart() != null) {
				    	   ((Rectangle)tile.getView()).setFill(Color.RED);
				       }
				    }
				};
				
				  EventHandler<InputEvent> handlerEntered = new EventHandler<InputEvent>() {
					    public void handle(InputEvent event) {
					    	//CONTROLLER ABOUT WHERE TO PUT
					    	/*
							    int i = gridPlayer.getRowIndex(tilePlayer.getView());
							    int j = gridPlayer.getColumnIndex(tilePlayer.getView());
							    
							    System.out.println(i + "  " + j);
							    ((Rectangle)tilePlayer.getView()).setFill(Color.GREENYELLOW);
						    	for(int offset = 1; offset <= 4 ; offset++) {
						    		Rectangle r = (Rectangle)gridPlayer.getChildren().get((j+offset)*10+i+1);
						    		r.setFill(Color.GREENYELLOW);
						    	}
						    */
					    }
					       
				  };
				  
				  EventHandler<InputEvent> handlerClicked = new EventHandler<InputEvent>() {
					    public void handle(InputEvent event) {
					    	
					    	if(hold != null) {
						    	int i = gridPlayer.getRowIndex(tilePlayer.getView());
								int j = gridPlayer.getColumnIndex(tilePlayer.getView());
								    
								hold.setVisible(false);
								
						       ((Rectangle)tilePlayer.getView()).setFill(Color.GREENYELLOW);
						    	for(int offset = 1; offset < holdSize ; offset++) {
						    		Rectangle r = (Rectangle)gridPlayer.getChildren().get((j+offset)*10+i+1);
						    		r.setFill(Color.GREENYELLOW);
						    	}
						    	
						    	hold = null;
					    	}
					    }
					};
			
				tile.getView().setOnMouseClicked(handler);
				
				tilePlayer.getView().setOnMouseEntered(handlerEntered);
				tilePlayer.getView().setOnMouseClicked(handlerClicked);
		   }
	   }
	   

	   GameManager.getInstance().startGame();
	   
   }
	

	
}
