package screen;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.ScreenManager;

public class Menu {

	
	@FXML public void startGameBoard(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(ScreenManager.GAME));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}