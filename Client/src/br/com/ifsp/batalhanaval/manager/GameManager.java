package br.com.ifsp.batalhanaval.manager;

import java.io.IOException;

import br.com.ifsp.batalhanaval.gameobjects.Board;
import br.com.ifsp.batalhanaval.gameobjects.Part;
import br.com.ifsp.batalhanaval.gameobjects.Player;
import br.com.ifsp.batalhanaval.gameobjects.Ship;
import br.com.ifsp.batalhanaval.screen.GameBoard;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameManager{
	
	enum STATES { ENDGAME,
				  START, WIN, LOSE, DRAW, 
				  YOURTURN, ENEMYTURN}
	
	STATES state;
	
	GameBoard game;
	
	private static GameManager instance;
	
	Player player;
	Player enemy;
	
	Board playerBoard;
	Board enemyBoard;
	
	private GameManager() throws IOException {
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.GAME));
		game = loader.getController();*/
	
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
	
	public void sendMessage(int i , int j) {
		game.hit(i, j);
	}
	
	public Board getEnemyBoard() {
		return enemyBoard;
	}
	
	public Board getPlayerBoard() {
		return playerBoard;
	}
	
	private void changeState(STATES newState) {
		switch(newState) {
			case ENDGAME:
					//Send Signal wich game end (possible send (-1, -1))
					break;
			case START:
					playerBoard = new Board(10, 10);
					enemyBoard = new Board(10, 10);
					player = new Player();
					enemy = new Player();
					break;
			case WIN:
					break;
			case LOSE:
					break;
			case DRAW:
					break;
			case YOURTURN:
					break;
			case ENEMYTURN:
					break;
		}
		
		state = newState;
	}
	
	public void startGame() {
		changeState(STATES.START);
		changeState(STATES.YOURTURN);
	}
	
	public boolean isGameEnd() {
		
		boolean isEnd = false;
		
		Ship[] ships = player.getShips();
		for(Ship ship : ships) {
			for(Part part : ship.getParts()) {
				if(part.isPartDestruct()) {
					isEnd = true;
				}
			}
		}
		
		return isEnd;
	}
	
	public void passTurn(int x, int y) {
		if(state == STATES.YOURTURN) {
			//Send coordinate (x, y) of board and verify if gameEND
			changeState(STATES.ENEMYTURN);
		}else {
			//Receive coordinate (x, y) of board and verify if gameEND
			changeState(STATES.YOURTURN);
		}
	}
}
