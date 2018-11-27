package br.ifsp.edu.batalhanaval.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{

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
			System.out.println("Exce��o I/O lan�ada na thread do servidor");
		}
	}
}