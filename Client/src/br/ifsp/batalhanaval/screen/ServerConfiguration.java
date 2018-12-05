package br.ifsp.batalhanaval.screen;

import java.io.IOException;

import br.ifsp.batalhanaval.manager.ScreenManager;
import br.ifsp.batalhanaval.manager.ServerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerConfiguration {

	@FXML
	TextField tfIp, tfPort;

	public void saveConfiguration(ActionEvent event) {
		
		ScreenManager.getInstance().setScreen(ScreenManager.INITSCREEN);
		ScreenManager.getInstance().showScreen();
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.INITSCREEN));
		Parent parent = null;

		try {
			parent = loader.load();
		} catch (IOException e) {
			System.out.println("Exceção não possível carregar a tela inicial.");
		}
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		 */
	}

	@FXML
	public void initialize() {
		tfIp.setText(ServerManager.getInstance().getIp());
		tfPort.setText(String.valueOf(ServerManager.getInstance().getPort()));
	}
}
