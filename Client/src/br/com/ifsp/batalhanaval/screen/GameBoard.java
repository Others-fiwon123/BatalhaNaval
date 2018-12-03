package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;
import br.com.ifsp.batalhanaval.gameobjects.Board;
import br.com.ifsp.batalhanaval.gameobjects.Tile;
import br.com.ifsp.batalhanaval.manager.GameManager;
import br.com.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
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
	int holdIdShip;
	
	public void  OnClickReady(MouseEvent evt) throws IOException {
		GameManager.getInstance().readyGame();
		System.out.println("enviado menssagem Ready");
	}
	
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
					((Rectangle)child).setFill(Color.AQUA);
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
			holdIdShip = 1;
		}else if(typeShip == viewEncouracado) {
			holdSize = 4;
			holdIdShip = 2;
		}else if(typeShip == viewCruzador) {
			holdSize = 3;
			holdIdShip = 3;
		}else if(typeShip == viewSubmarino) {
			holdSize = 2;
			holdIdShip = 4;
		}
		
		hold = (ImageView)evt.getSource();
		hold.setOpacity(1);
	}
	
	@FXML
    public void initialize() throws IOException {
	   Board board = new Board(10, 10);
	   
	   for(int i = 0; i < 10; i++) {
		   for(int j = 0; j < 10; j++) {
			   Tile tilePlayer = new Tile(null);
			   Tile tileEnemy = new Tile(null);

			   gridPlayer.add(tilePlayer, i, j, 1, 1);
			   gridEnemy.add(tileEnemy,i, j, 1, 1);
			   
			   EventHandler<InputEvent> handlerTileEnemy = new EventHandler<InputEvent>() {
				    public void handle(InputEvent event) {
				    	tileEnemy.setFill(Color.AQUA);
				       
				       int i = gridPlayer.getRowIndex(tileEnemy);
				       int j = gridPlayer.getColumnIndex(tileEnemy);
				       try {
						GameManager.getInstance().sendMessage(i, j);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				       
				       if(tileEnemy.getPart() != null) {
				    	   tileEnemy.setFill(Color.RED);
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
						    	int i = gridPlayer.getRowIndex(tilePlayer);
								int j = gridPlayer.getColumnIndex(tilePlayer);
								    
						    	int lastPosition = (j+holdSize-1)*10+i+1;
								
						    	//Verifica se pode por o navio na possição
						    	if(lastPosition <= 100) {
									hold.setVisible(false);
									
									try {
										GameManager.getInstance().setConfigurationShip(holdIdShip, true, i, j);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									tilePlayer.setFill(Color.GREENYELLOW);
							    	for(int offset = 1; offset < holdSize ; offset++) {
							    		Rectangle r = (Rectangle)gridPlayer.getChildren().get((j+offset)*10+i+1);
							    		r.setFill(Color.GREENYELLOW);
							    	}
							    	
							    	hold = null;
						    	}
					    	}
					    	
					    }
					};
			
				tileEnemy.setOnMouseClicked(handlerTileEnemy);
				
				tilePlayer.setOnMouseEntered(handlerEntered);
				tilePlayer.setOnMouseClicked(handlerClicked);
		   }
	   }
	   

	   GameManager.getInstance().startGame();
	   
   }
	

	
}
