package br.com.ifsp.batalhanaval.manager;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandle implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost", 7777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
