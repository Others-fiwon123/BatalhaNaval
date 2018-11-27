package br.com.ifsp.batalhanaval.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import br.ifsp.edu.batalhanaval.thread.ServerThread;

public class GameServer {

	private static ServerSocket serverSocket;
	
	GameServer ( int port ) throws IOException {
		serverSocket = new ServerSocket( port );
	}
	
	private Socket aceitaConexao() throws IOException {
		return serverSocket.accept();
	}
	
	public static void main(String[] args) throws IOException {
		int numero, temp;
		GameServer server = new GameServer( 7777 );
		System.out.println("Aguardando uma conexao...");
		
		try {
			while ( true ) {
				Socket socket = server.aceitaConexao();
				//////////////////////////////////////////////////////////
				Scanner sc = new Scanner( socket.getInputStream() );	
				numero = sc.nextInt();
				temp = numero * 2;	
				PrintStream p = new PrintStream(socket.getOutputStream());		
				p.println(temp);
				//////////////////////////////////////////////////////////
				
				Thread thread = new Thread(new ServerThread( socket ));
				thread.start();
				
			}
			
		} catch (IOException e) {
			// tratar
		}
	}
}
 