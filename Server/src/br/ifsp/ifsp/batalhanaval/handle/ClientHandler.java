/**
 * 
 */
package br.ifsp.ifsp.batalhanaval.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{

	BufferedReader reader;
    Socket sock;
    PrintWriter client;
    
    Socket playerSocket;
    Socket enemySoket;
    PrintWriter sendPlayer;
    BufferedReader hearEnemy;

    public ClientHandler(Socket clientSocket, PrintWriter user) 
    {
         client = user;
         try 
         {
             sock = clientSocket;
             InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
             reader = new BufferedReader(isReader);
         }
         catch (Exception ex) 
         {
             //Tratar
         }

    }
    
    public ClientHandler(Socket playerSocket, Socket enemySocket) throws IOException 
    {
         this.playerSocket = playerSocket;
         this.enemySoket = enemySocket;
         
         InputStreamReader isReader = new InputStreamReader(enemySoket.getInputStream());
         hearEnemy = new BufferedReader(isReader);
         
         sendPlayer = new PrintWriter(playerSocket.getOutputStream(), true);


    }

    @Override
    public void run() 
    {
         String message, 
         connect = "Connect", disconnect = "Disconnect", youStart = "YouStart", hit = "Hit", ready = "Ready" ;
         String[] data;

         try 
         {
        	 while ((message = hearEnemy.readLine()) != null) {
        		 
        		 data = message.split(":");
        		 if(data[0].equals(connect)) {
        			 sendPlayer.println(connect);
        		 }else if(data[0].equals(hit)) {
        			 sendPlayer.println(message);
        		 }else if(data[0].equals(ready)) {
        			 sendPlayer.println(message);
        		 }else if(data[0].equals(youStart)) {
        			 sendPlayer.println(message);
        		 }
        	 }
          } 
          catch (Exception ex) 
          {
             ex.printStackTrace();
          } 
	} 
}
