package br.ifsp.batalhanaval.screen;

import java.io.IOException;

import br.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
