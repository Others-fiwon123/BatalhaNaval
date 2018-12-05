package br.ifsp.ifsp.batalhanaval.thread;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;

import br.ifsp.ifsp.batalhanaval.handle.ClientHandler;


public class ServerThread implements Runnable{

	protected ArrayList<String> users;
	private ServerSocket serverSock = null;
	
	@Override
    public void run() 
    {
        //clientOutputStreams = new ArrayList();
        users = new ArrayList();
        try 
        {
        	serverSock = new ServerSocket(7777);

            while (true) 
            {
            	Socket clientSock = serverSock.accept();
            	Socket clientSock2 = serverSock.accept();

            	Thread listener = new Thread(new ClientHandler(clientSock, clientSock2));
            	Thread listener2 = new Thread(new ClientHandler(clientSock2, clientSock));
            	
            	listener.start();
            	listener2.start();
            	
            	PrintWriter writer = new PrintWriter(clientSock.getOutputStream(), true);
            	PrintWriter writer2 = new PrintWriter(clientSock2.getOutputStream(), true);
            	
            	int whoStart = new Random().nextInt(2);
            	if(whoStart == 1) {
            		writer.println("YouStart:True");
            		writer2.println("YouStart:False");
            	}else {
            		writer.println("YouStart:False");
            		writer2.println("YouStart:True");
            	}
            }
        } catch (SocketException e) {
			System.out.println("Exceção lançada a partir do Socket da Thread do servidor");
		} catch (Exception e) {
			System.out.println("Exceção I/O lançada na thread do servidor");
		}
    }
	
}