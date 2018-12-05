package br.ifsp.batalhanaval.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import br.ifsp.batalhanaval.gameobjects.Board;
import br.ifsp.batalhanaval.gameobjects.Part;
import br.ifsp.batalhanaval.gameobjects.Player;
import br.ifsp.batalhanaval.gameobjects.Ship;
import br.ifsp.batalhanaval.gameobjects.Tile;
import br.ifsp.batalhanaval.screen.GameBoard;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class GameManager {

	public enum STATES {
		ENDGAME, START, WIN, LOSE, YOURTURN, ENEMYTURN
	}

	STATES state;
	GameBoard game;

	static GameManager instance;

	Player player;
	Player enemy;

	Board boardPattern;

	Socket socket;

	boolean isReadyEnemy, isReadyPlayer;

	private GameManager() {
		isReadyEnemy = false;
		isReadyPlayer = false;
		boardPattern = new Board(10, 10);
	}

	public Board getBoardPattern() {
		return boardPattern;
	}

	public STATES getState() {
		return state;
	}

	public boolean isGameReady() {
		if (isReadyEnemy && isReadyPlayer) {
			return true;
		}

		return false;
	}

	public void configureBoardEnemy() {
		Ship[] ships = enemy.getShips();

		for (int i = 0; i < ships.length; i++) {
			Ship ship = ships[i];
			for (int offset = 0; offset < ship.getSize(); offset++) {
				Tile t = (Tile) game.getGridEnemy().getChildren()
						.get((ship.getFistPositionColumn() + offset) * 10 + ship.getFistPositionRow() + 1);
				t.setPart(ship.getParts()[offset]);
			}
		}
		isReadyEnemy = true;
		game.enemyReady();
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

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}

		return instance;
	}

	public void hit(int i, int j) {
		game.hit(i, j);
	}

	public void sendMessage(int i, int j) {
		try {
			new PrintWriter(socket.getOutputStream(), true).println("Hit:" + i + ":" + j);
		} catch (IOException e) {
			System.out.println("Exception Server Indisponível");
		}
	}

	public void enemyDisconect() {
		try {
			new PrintWriter(socket.getOutputStream(), true).println("Disconnect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readyGame() {
		Ship[] ships = player.getShips();
		String message = "Ready";
		for (int i = 0; i < ships.length; i++) {
			message += ":" + ships[i].getId() + "-" + ships[i].getHorizontal() + "-" + ships[i].getFistPositionRow()
					+ "-" + ships[i].getFistPositionColumn();
		}

		try {
			new PrintWriter(socket.getOutputStream(), true).println(message);
		} catch (IOException e) {
			System.out.println("Exception Server Indisponível");
		}
		isReadyPlayer = true;
		game.playerReady();

	}

	public void setConfigurationShip(int id, boolean isHorizontal, int firstPositionRow, int firstPositinColumn) {
		Ship ship = player.getShips()[id - 1];
		ship.setHorizontal(isHorizontal);
		ship.setFistPositionRow(firstPositionRow);
		ship.setFistPositionColumn(firstPositinColumn);

	}

	public void changeState(STATES newState) {
		switch (newState) {
		case ENDGAME:
			Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                    Alert alertLose = new Alert(AlertType.INFORMATION);
			                    alertLose.setTitle("Information Dialog");
			                    alertLose.setHeaderText(null);
			                    alertLose.setContentText("Enemy Disconect!");

			                    alertLose.showAndWait();
			                }
			            });
			break;
		case START:
			player = new Player(10, 10);
			enemy = new Player(10, 10);
			break;
		case WIN:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alertWin = new Alert(AlertType.INFORMATION);
					alertWin.setTitle("Information Dialog");
					alertWin.setHeaderText(null);
					alertWin.setContentText("YOU WIN!");

					alertWin.showAndWait();
				}

			});
			break;
		case LOSE:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alertLose = new Alert(AlertType.INFORMATION);
					alertLose.setTitle("Information Dialog");
					alertLose.setHeaderText(null);
					alertLose.setContentText("YOU LOSE!");

					alertLose.showAndWait();
				}
			});
			break;
		case YOURTURN:
			game.getCirclePlayer().setFill(Color.RED);
			game.getCircleEnemy().setFill(Color.GRAY);
			break;
		case ENEMYTURN:
			game.getCirclePlayer().setFill(Color.GRAY);
			game.getCircleEnemy().setFill(Color.RED);
			break;
		}

		state = newState;
	}

	public void verifyShipDestroyed(int idShip) {
		Ship ship = enemy.getShips()[idShip - 1];
		Part[] parts = ship.getParts();

		boolean isShipDestroyed = true;

		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].isPartDestruct()) {
				isShipDestroyed = false;
			}
		}

		if (isShipDestroyed) {
			game.showMessageShip(idShip);
		} else {
			game.hideMessageShip();
		}
	}

	public void startGame() {
		changeState(STATES.START);
	}

	public boolean isGameEndPlayer() {

		boolean isEnd = true;

		Ship[] ships = player.getShips();
		for (Ship ship : ships) {
			for (Part part : ship.getParts()) {
				if (!part.isPartDestruct()) {
					isEnd = false;
				}
			}
		}

		return isEnd;
	}

	public boolean isGameEndEnemy() {

		boolean isEnd = true;

		Ship[] ships = enemy.getShips();
		for (Ship ship : ships) {
			for (Part part : ship.getParts()) {
				if (!part.isPartDestruct()) {
					isEnd = false;
				}
			}
		}

		return isEnd;
	}

	public void passTurn(int i, int j) {
		changeState(STATES.ENEMYTURN);
		sendMessage(i, j);
	}
}
