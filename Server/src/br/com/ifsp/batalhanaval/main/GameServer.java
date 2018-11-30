package br.com.ifsp.batalhanaval.main;

import java.io.IOException;

import br.ifsp.ifsp.batalhanaval.thread.ServerThread;

public class GameServer {

	private static GameServer servidor;
	
	public static void main(String[] args) throws IOException {
		try {
			servidor.iniciaServidor();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void iniciaServidor() throws IOException {
		Thread thread = new Thread( new ServerThread() );
		thread.start();
	}
}