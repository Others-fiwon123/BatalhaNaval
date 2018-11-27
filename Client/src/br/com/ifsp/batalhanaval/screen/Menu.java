package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

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
		// Teste de comunicao com o server
		/*int numero, temp;
		Scanner entrada = new Scanner(System.in);
		Socket socket = new Socket("localhost", 7777);
		
		Scanner scanner = new Scanner(socket.getInputStream());
		System.out.println("Entre com um valor: ");
		numero = entrada.nextInt();
		
		PrintStream p = new PrintStream(socket.getOutputStream());
		p.println(numero);
		
		temp = scanner.nextInt();
		System.out.println(temp);*/
		
		
		Parent parent = FXMLLoader.load(getClass().getResource(ScreenManager.GAME));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
