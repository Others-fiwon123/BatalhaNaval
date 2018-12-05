package br.ifsp.batalhanaval.screen;

import br.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InitScreen {

	@FXML
	public void startMenu(ActionEvent event) {

		// Inicia tela de menu Menu
		ScreenManager.getInstance().setScreen(ScreenManager.MENU);
		ScreenManager.getInstance().showScreen();

	}

	@FXML
	public void startServerConfig(ActionEvent event) {

		ScreenManager.getInstance().setScreen(ScreenManager.SERVERCONFIGURATION);
		ScreenManager.getInstance().showScreen();
	
	}
}
