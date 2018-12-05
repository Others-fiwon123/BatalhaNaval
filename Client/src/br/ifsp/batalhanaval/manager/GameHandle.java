package br.ifsp.batalhanaval.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import br.ifsp.batalhanaval.gameobjects.Player;
import br.ifsp.batalhanaval.gameobjects.Ship;
import javafx.stage.Stage;

public class GameHandle implements Runnable {

	BufferedReader hearEnemy;
	Stage stage;

	public GameHandle(Stage stage, Socket socket) {
		this.stage = stage;

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
		String message, connect = "Connect", disconnect = "Disconnect", hit = "Hit", ready = "Ready",
				youStart = "YouStart";
		String[] data;

		try {
			while ((message = hearEnemy.readLine()) != null) {

				data = message.split(":");
				if (data[0].equals(connect)) {
					// System.out.println("CONECTADOS");
				} else if (data[0].equals(hit)) {
					int i = Integer.valueOf(data[1]);
					int j = Integer.valueOf(data[2]);
					GameManager.getInstance().hit(i, j);
				} else if (data[0].equals(ready)) {
					Player enemy = GameManager.getInstance().getEnemy();
					for (int i = 1; i < data.length; i++) {
						String[] infoShip = data[i].split("-");
						Ship ship = enemy.getShips()[Integer.valueOf(infoShip[0]) - 1];
						ship.setHorizontal(Boolean.valueOf(infoShip[1]));
						ship.setFistPositionRow(Integer.valueOf(infoShip[2]));
						ship.setFistPositionColumn(Integer.valueOf(infoShip[3]));

					}
					GameManager.getInstance().configureBoardEnemy();
				} else if (data[0].equals(youStart)) {
					if (Boolean.valueOf(data[1]))
						GameManager.getInstance().changeState(GameManager.STATES.YOURTURN);
					else
						GameManager.getInstance().changeState(GameManager.STATES.ENEMYTURN);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
