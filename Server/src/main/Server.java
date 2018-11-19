package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	static ServerSocket serverSocket;
	
	//Geracao da porta do Servidor (Socket)
	private void criarSocket ( int port ) throws IOException {
		serverSocket = new ServerSocket( port );
	}
	
	//Espera por um pedido de conexao
	private Socket aguardaConexao() throws IOException {
		return serverSocket.accept();
	}
	
	//Fechar a conexao com o servidor
	private void fecharConexao( Socket socket ) throws IOException {
			socket.close();
	}
	
	private void tratarConexao(Socket socket) throws IOException {
		try {
			ObjectOutputStream output = new ObjectOutputStream( socket.getOutputStream() );
			output.flush();
			ObjectInputStream input = new ObjectInputStream( socket.getInputStream() );
		
			System.out.println("Tratamento de conexao...");
			String msg = input.readUTF();
			System.out.println("Mensagem recebida: " + msg);
			
			input.close();
			output.close();
			
		} catch (IOException ex) {
			// TODO: TRATAR EXCEPTION
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			fecharConexao(socket);
		}
	}

	
	public static void main(String[] args) throws IOException {
		
		Server server = new Server();
		System.out.println("Aguardando uma conexao...");
		server.criarSocket(8080);
		
		try {
			
			while ( true ) {
				Socket socket = server.aguardaConexao();
				server.tratarConexao( socket );
				
				//BufferedReader readerChannel = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
				//BufferedWriter writerChannel = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
			
			}
						
		} catch (IOException e) {
			// tratar
		}
		
	}
}
