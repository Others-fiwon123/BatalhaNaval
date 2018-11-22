package br.com.ifsp.batalhanaval.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.ifsp.batalhanaval.manager.GameManager;
import br.com.ifsp.batalhanaval.manager.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
	
	public static void  main(String args[]) throws UnknownHostException, IOException {
		
		// Teste de comunicao com o server
		int numero, temp;
		Scanner entrada = new Scanner(System.in);
		Socket socket = new Socket("localhost", 7777);
		
		Scanner scanner = new Scanner(socket.getInputStream());
		System.out.println("Entre com um valor: ");
		numero = entrada.nextInt();
		
		PrintStream p = new PrintStream(socket.getOutputStream());
		p.println(numero);
		
		temp = scanner.nextInt();
		System.out.println(temp);
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource(ScreenManager.LOGIN));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
