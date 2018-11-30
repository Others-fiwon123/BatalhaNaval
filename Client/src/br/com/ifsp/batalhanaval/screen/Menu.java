package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import br.com.ifsp.batalhanaval.manager.GameHandle;
import br.com.ifsp.batalhanaval.manager.GameManager;
import br.com.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu {

	
	@FXML public void startGameBoard(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.GAME));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		GameManager.getInstance().setGameController(loader.getController());
		
		//Inicia comunicação com o servidor
		Thread t = new Thread(new GameHandle(stage));
		t.start();
	}
}
