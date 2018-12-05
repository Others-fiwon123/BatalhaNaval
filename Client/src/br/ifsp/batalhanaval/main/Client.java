
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
		
		/*Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource(ScreenManager.INITSCREEN));
		} catch (IOException e1) {
			System.out.println("Exceção não possível carregar a tela inicial.");
		}
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		*/
	}
}
