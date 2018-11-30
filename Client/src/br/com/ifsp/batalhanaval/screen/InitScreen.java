package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import br.com.ifsp.batalhanaval.manager.ScreenManager;
import br.com.ifsp.batalhanaval.manager.ServerHandle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitScreen {
	
	@FXML public void startMenu(ActionEvent event) throws IOException {
		//int numero, temp;

		
		//Thread thread = new Thread( new ServerHandle() );
		//thread.start();
		//Scanner scanner = new Scanner(socket.getInputStream());
		/*System.out.println("Entre com um valor: ");
		numero = entrada.nextInt();
		
		PrintStream p = new PrintStream(socket.getOutputStream());
		p.println(numero);
		
		temp = scanner.nextInt();
		System.out.println(temp);*/
		
		
		Parent parent = FXMLLoader.load(getClass().getResource(ScreenManager.MENU));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
