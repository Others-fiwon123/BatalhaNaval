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
		Parent parent = null;
		
		try {
			parent = FXMLLoader.load(getClass().getResource(ScreenManager.MENU));
		} catch (IOException e) {
			System.out.println("Exceção não possível carregar a tela Menu.");
		}
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	public void startServerConfig(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.SERVERCONFIGURATION));
		Parent parent = null;
		
		try {
			parent = loader.load();
		} catch (IOException e) {
			System.out.println("Exceção não possível carregar a tela Configuração do Servidor.");
		}
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
	
	}
}
