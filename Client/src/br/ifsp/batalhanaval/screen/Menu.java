package br.ifsp.batalhanaval.screen;

import java.io.IOException;
import java.net.Socket;

import br.ifsp.batalhanaval.manager.GameHandle;
import br.ifsp.batalhanaval.manager.GameManager;
import br.ifsp.batalhanaval.manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu {

	@FXML
	public void startGameBoard(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.GAMEBOARD));
		Parent parent;
		try {
			parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			GameManager.getInstance().setGameController(loader.getController());

			// Inicia comunicação com o servidor
			Socket socket = new Socket("localhost", 7777);
			GameManager.getInstance().setSocket(socket);
			Thread t = new Thread(new GameHandle(stage, socket));
			t.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
