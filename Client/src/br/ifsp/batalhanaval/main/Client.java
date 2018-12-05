
package br.ifsp.batalhanaval.main;

import java.io.IOException;

import br.ifsp.batalhanaval.manager.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		ScreenManager.getInstance().setStage(primaryStage);
		ScreenManager.getInstance().showScreen();
	}
}
