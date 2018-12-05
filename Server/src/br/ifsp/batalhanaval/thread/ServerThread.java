package br.ifsp.batalhanaval.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

import br.ifsp.batalhanaval.handle.ClientHandler;

public class ServerThread implements Runnable {

	private ServerSocket serverSock = null;

	@Override
	public void run() {
		try {
			serverSock = new ServerSocket(7777);

			while (true) {
				Socket clientSock = serverSock.accept();
				Socket clientSock2 = serverSock.accept();

				clientTransmiter(clientSock, clientSock2);
			}
		} catch (SocketException e) {
			System.out.println("Exceção lançada a partir do Socket da Thread do servidor");
		} catch (Exception e) {
			System.out.println("Exceção I/O lançada na thread do servidor");
		}
	}

	private void clientTransmiter(Socket clientSock, Socket clientSock2) throws IOException {
		startListeners(new Thread(new ClientHandler(clientSock, clientSock2)),
				new Thread(new ClientHandler(clientSock2, clientSock)));

		initPlayerRandomizer(new Random().nextInt(2), new PrintWriter(clientSock.getOutputStream(), true),
				new PrintWriter(clientSock2.getOutputStream(), true));
	}

	private void startListeners(Thread listener, Thread listener2) {
		listener.start();
		listener2.start();
	}

	private void initPlayerRandomizer(int whoStart, PrintWriter writer, PrintWriter writer2) {
		if (whoStart == 1) {
			writer.println("YouStart:True");
			writer2.println("YouStart:False");
		} else {
			writer.println("YouStart:False");
			writer2.println("YouStart:True");
		}
	}

}