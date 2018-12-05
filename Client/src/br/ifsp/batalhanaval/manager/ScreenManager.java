package br.ifsp.batalhanaval.manager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {

	public static final String INITSCREEN = "../screen/InitScreen.fxml";
	public static final String MENU = "../screen/Menu.fxml";
	public static final String GAMEBOARD = "../screen/GameBoard.fxml";
	public static final String SERVERCONFIGURATION = "../screen/ServerConfiguration.fxml";
	
	static ScreenManager instance;
	
	String currentScreen;
	Stage stage;
	FXMLLoader loader;
	Parent parent;
	
	private ScreenManager() {
		currentScreen = INITSCREEN;
		parent = null;
	}
	
	public static ScreenManager getInstance() {
		
		if(instance == null) {
			instance = new ScreenManager();
		}
		
		return instance;
	}
	
	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
	public void setScreen(String newScreen) {
		
		switch (newScreen){
			case INITSCREEN:
			case MENU:
			case GAMEBOARD:
			case SERVERCONFIGURATION:
				currentScreen = newScreen;
				break;
			default:
				System.out.println("Caminho de tela inválido");
				break;
		}
	}
	
	public void showScreen() {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(currentScreen));
		
		try {
			parent = loader.load();
		} catch (IOException e1) {
			System.out.println("Exceção não possível carregar a tela atual");
		}
		
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public FXMLLoader getLoader() {
		return loader;
	}

}
