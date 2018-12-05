
package br.com.ifsp.batalhanaval.main;

import br.com.ifsp.batalhanaval.manager.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
	
	public static void  main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		try {
			
			//Inicia Tela inicial
			Parent root = FXMLLoader.load(getClass().getResource(ScreenManager.INITSCREEN));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
