package br.ifsp.batalhanaval.main;

import java.io.IOException;

import br.ifsp.batalhanaval.thread.ServerThread;

public class GameServer {

	private static GameServer servidor;

	public static void main(String[] args) throws IOException {
		try {
			servidor = new GameServer();
			servidor.startGameServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startGameServer() throws IOException {
		Thread thread = new Thread(new ServerThread());
		thread.start();
	}
}