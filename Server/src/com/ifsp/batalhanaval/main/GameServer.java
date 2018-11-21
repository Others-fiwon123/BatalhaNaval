package com.ifsp.batalhanaval.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {

	static ServerSocket serverSocket;
	
	//Geracao da porta do Servidor (Socket)
	private void criarServerSocket ( int port ) throws IOException {
		serverSocket = new ServerSocket( port );
	}
	
	//Espera por um pedido de conexao
	private Socket aguardaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}
	
	public static void main(String[] args) throws IOException {
		
		GameServer server = new GameServer();
		System.out.println("Aguardando uma conexao...");
		server.criarServerSocket(7777);
		
		int numero, temp;
		
		try {
			Socket socket = server.aguardaConexao();
				
			//Teste de conexão			
			Scanner sc = new Scanner( socket.getInputStream() );			
			numero = sc.nextInt();			
			temp = numero * 2;			
						
			PrintStream p = new PrintStream(socket.getOutputStream());		
			p.println(temp);
			//System.out.println(socket.getInetAddress());
			socket.close();
						
		} catch (IOException e) {
			// tratar
		}
		
	}
}
 