package br.com.ifsp.batalhanaval.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import br.com.ifsp.batalhanaval.gameobjects.Part;
import br.com.ifsp.batalhanaval.gameobjects.Player;
import br.com.ifsp.batalhanaval.gameobjects.Ship;
import br.com.ifsp.batalhanaval.gameobjects.Tile;
import br.com.ifsp.batalhanaval.screen.GameBoard;
import javafx.scene.paint.Color;

public class GameManager{
	
	public enum STATES { ENDGAME,
				  START, WIN, LOSE, 
				  YOURTURN, ENEMYTURN}
	
	STATES state;
	
	GameBoard game;
	
	private static GameManager instance;
	
	Player player;
	Player enemy;
	
	Socket socket;
	
	private GameManager() throws IOException {
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.GAME));
		game = loader.getController();*/
	
	}
	
	public STATES getState() {
		return state;
	}
	
	public void configureBoardEnemy() {
		Ship[] ships = enemy.getShips();
		
		for(int i =  0; i < ships.length; i++) {
	    	Ship ship = ships[i];
			for(int offset = 0; offset < ship.getSize() ; offset++) {
	    		Tile t = (Tile)game.gridEnemy.getChildren().get((ship.getFistPositionColumn()+offset)*10+
	    																	ship.getFistPositionRow()+1);
	    		t.setPart(ship.getParts()[offset]);
	    	}
		}
	}
	
	public Player getEnemy() {
		return enemy;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void setGameController(GameBoard gameBoard) {
		game = gameBoard;
	}
	
	public static GameManager getInstance() throws IOException {
		if(instance == null) {
			instance = new GameManager();
		}
		
		return instance;
	}
	public void sendMessage() throws IOException  {
		new PrintWriter(socket.getOutputStream(), true).println("Connect");
	}
	
	public void hit(int i, int j) {
		game.hit(i, j);
	}
	
	public void sendMessage(int i ,int j) throws IOException  {
		new PrintWriter(socket.getOutputStream(), true).println("Hit:"+i+":"+j);
	}
	
	public void readyGame() {
		Ship[] ships = player.getShips();
		String message = "Ready";
		for(int i=0; i<ships.length; i++) {
			message += ":"+ships[i].getId() + "-" +  ships[i].getHorizontal() + "-" + ships[i].getFistPositionRow() + "-" +
																				 ships[i].getFistPositionColumn();																		
		}
		try {
			new PrintWriter(socket.getOutputStream(), true).println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setConfigurationShip(int id, boolean isHorizontal, int firstPositionRow, int firstPositinColumn) {	
		Ship ship = player.getShips()[id-1];
		ship.setHorizontal(isHorizontal);
		ship.setFistPositionRow(firstPositionRow);
		ship.setFistPositionColumn(firstPositinColumn);
		
	}
	
	public void changeState(STATES newState) {
		switch(newState) {
			case ENDGAME:
					//Send Signal wich game end (possible send (-1, -1))
					break;
			case START:
					player = new Player(10, 10);
					enemy = new Player(10, 10);
					break;
			case WIN:
					/*Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("YOU WIN!");
		
					alert.showAndWait();*/
					break;
			case LOSE:		
					/*Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Information Dialog");
					alert1.setHeaderText(null);
					alert1.setContentText("YOU LOSE!");
			
					alert1.showAndWait()*/;
					break;
			case YOURTURN:
					game.circlePlayer.setFill(Color.RED);
					game.circleEnemy.setFill(Color.GRAY);
					break;
			case ENEMYTURN:
					game.circlePlayer.setFill(Color.GRAY);
					game.circleEnemy.setFill(Color.RED);
					break;
		}
		
		state = newState;
	}
	
	public void verifyShipDestroyed(int idShip) {
		Ship ship = enemy.getShips()[idShip-1];
		Part[] parts = ship.getParts();
		
		boolean isShipDestroyed = true;
		
		for (int i = 0; i < parts.length; i++) {
			if(!parts[i].isPartDestruct()) {
				isShipDestroyed = false;
			}
		}
		
		if(isShipDestroyed) {
			game.showMessageShip(idShip);
		}else {
			game.hideMessageShip();
		}
	}
	
	public void startGame() {
		changeState(STATES.START);
	}
	
	public boolean isGameEndPlayer() {
		
		boolean isEnd = true;
		
		Ship[] ships = player.getShips();
		for(Ship ship : ships) {
			for(Part part : ship.getParts()) {
				if(!part.isPartDestruct()) {
					isEnd = false;
				}
			}
		}
		
		return isEnd;
	}
	
	public boolean isGameEndEnemy() {
		
		boolean isEnd = true;
		
		Ship[] ships = enemy.getShips();
		for(Ship ship : ships) {
			for(Part part : ship.getParts()) {
				if(!part.isPartDestruct()) {
					isEnd = false;
				}
			}
		}
		
		return isEnd;
	}
	
	public void passTurn(int i, int j) {
		changeState(STATES.ENEMYTURN);
		try {
			sendMessage(i, j);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
