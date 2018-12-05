package br.ifsp.batalhanaval.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import br.ifsp.batalhanaval.gameobjects.Player;
import br.ifsp.batalhanaval.gameobjects.Ship;
import br.ifsp.batalhanaval.manager.GameManager;

public class GameHandle implements Runnable {

	BufferedReader hearEnemy;
	String message, connect = "Connect", disconnect = "Disconnect", hit = "Hit", ready = "Ready", youStart = "YouStart";

	public GameHandle(Socket socket) {
		InputStreamReader isReader;
		try {
			isReader = new InputStreamReader(socket.getInputStream());
			hearEnemy = new BufferedReader(isReader);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			while ((message = hearEnemy.readLine()) != null) {
				filterGameActions(message.split(":"), message);
			}
		} catch (SocketException ex) {
			System.out.println("Problemas com a conexão entre cliente e servidor.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void filterGameActions(String[] data, String message) throws IOException {
		if (data[0].equals(hit)) {
			hitPlayer(data);
		} else if (data[0].equals(ready)) {
			setShipInformations(GameManager.getInstance().getEnemy(), data);
		} else if (data[0].equals(youStart)) {
			changeTurn(Boolean.valueOf(data[1]));
		}
	}

	private void setShipInformations(Player enemy, String[] data) throws IOException {
		for (int i = 1; i < data.length; i++) {
			String[] infoShip = data[i].split("-");
			Ship ship = enemy.getShips()[Integer.valueOf(infoShip[0]) - 1];
			ship.setHorizontal(Boolean.valueOf(infoShip[1]));
			ship.setFistPositionRow(Integer.valueOf(infoShip[2]));
			ship.setFistPositionColumn(Integer.valueOf(infoShip[3]));
		}
		GameManager.getInstance().configureBoardEnemy();
	}

	private void changeTurn(Boolean turn) throws IOException {
		if (Boolean.valueOf(turn))
			GameManager.getInstance().changeState(GameManager.STATES.YOURTURN);
		else
			GameManager.getInstance().changeState(GameManager.STATES.ENEMYTURN);
	}

	private void hitPlayer(String[] data) throws IOException {
		int i = Integer.valueOf(data[1]);
		int j = Integer.valueOf(data[2]);
		GameManager.getInstance().hit(i, j);
	}

}
