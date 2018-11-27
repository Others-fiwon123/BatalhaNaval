package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;

import br.com.ifsp.batalhanaval.gameobjects.Board;
import br.com.ifsp.batalhanaval.gameobjects.Tile;
import br.com.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameBoard {
	
	@FXML 
	Pane panePlayer;
	
	@FXML
	Label lbPortaAviao,
		  lbEncouracado,
		  lbCruzador,
		  lbSubmarino;
	
	@FXML 
	GridPane gridPlayer, gridEnemy; 
	
	@FXML public void startMenu(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(ScreenManager.MENU));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	
   @FXML
    public void initialize() {
	   
	   Board board = new Board(10, 10);
	   
	  // gridPlayer.setPrefHeight(30);
	   gridPlayer.setPrefWidth(30);
	   
	   for(int i = 0; i < 10; i++) {
		   for(int j = 0; j < 10; j++) {
			   gridPlayer.add(new Tile(null).getView(),i, j, 1, 1);
			   gridEnemy.add(new Tile(null).getView(),i, j, 1, 1);
		   }
	   }
	   

	   
   }
	

	
}
