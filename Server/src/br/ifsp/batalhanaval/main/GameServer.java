package br.ifsp.batalhanaval.main;

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
				
				ServerThread thread = new ServerThread(socket);
				thread.start();
				
			}
			
		} catch (IOException e) {
			// tratar
		}
	}
	
	static class ServerThread extends Thread{

		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter pWriter = null;
		
		public ServerThread( Socket socket ) {
			this.socket = socket;
		}
				
				
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pWriter = new PrintWriter(socket.getOutputStream());
				
			} catch (Exception e) {
				System.out.println("Exceção I/O lançada na thread do servidor");
			}
		}

	}
}
 