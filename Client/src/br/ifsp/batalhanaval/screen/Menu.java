package br.ifsp.batalhanaval.screen;

import java.io.IOException;
import java.net.Socket;

import br.ifsp.batalhanaval.handle.GameHandle;
import br.ifsp.batalhanaval.manager.GameManager;
import br.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Menu {

	@FXML
	public void startGameBoard(ActionEvent event) throws IOException {
		
		
		ScreenManager.getInstance().setScreen(ScreenManager.GAMEBOARD);
		ScreenManager.getInstance().showScreen();
		
		GameManager.getInstance().setGameController(ScreenManager.getInstance().getLoader().getController());

		// Inicia comunicação com o servidor
		Socket socket = new Socket("localhost", 7777);
		GameManager.getInstance().setSocket(socket);
		Thread t = new Thread(new GameHandle(socket));
		t.start();
	}
}
