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
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameBoard{
	
	@FXML 
	public Pane panePlayer;
	
	@FXML
	public Label lbPortaAviao,
		  lbEncouracado,
		  lbCruzador,
		  lbSubmarino;
	
	@FXML 
	public GridPane gridPlayer, gridEnemy; 
	
	@FXML public void startMenu(ActionEvent event) throws IOException {
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
	
	@FXML
    public void initialize() throws IOException {
	   Board board = new Board(10, 10);
	   
	   gridPlayer.setPrefHeight(30);
	   gridPlayer.setPrefWidth(30);
	   
	   for(int i = 0; i < 10; i++) {
		   for(int j = 0; j < 10; j++) {
			   Tile tile = new Tile(null);

			   gridPlayer.add(new Tile(null).getView(), i, j, 1, 1);
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
			
				tile.getView().setOnMouseClicked(handler);
		   }
	   }
	   

	   GameManager.getInstance().startGame();
	   
   }
	

	
}
