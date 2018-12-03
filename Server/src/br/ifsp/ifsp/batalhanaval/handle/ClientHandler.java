/**
 * 
 */
package br.ifsp.ifsp.batalhanaval.handle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ClientHandler extends Thread{

	BufferedReader reader;
    Socket sock;
    PrintWriter client;
    
    //Felipe
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
         String message, connect = "Connect", disconnect = "Disconnect", yourTurn = "YourTurn" ;
         String[] data;

         try 
         {
        	 while ((message = hearEnemy.readLine()) != null) {
        		 
        		 System.out.println("RECEBI SERVER");
        		 data = message.split(":");
        		 if(data[0].equals(connect)) {
        			 sendPlayer.println(connect);
        		 }else if(data[0].equals("Hit")) {
        			 sendPlayer.println(message);
        		 }else if(data[0].equals("Ready")) {
        			 sendPlayer.println(message);
        		 }else if(data[0].equals(yourTurn)) {
        			 sendPlayer.println(message);
        		 }
        	 }
        	 
             /*while ((message = reader.readLine()) != null) 
             {
                 
                 data = message.split(":");
                 
                 for (String token:data) 
                 {
                     //ta_chat.append(token + "\n");
                 }

                 if (data[2].equals(connect)) 
                 {
                     //tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                     //userAdd(data[0]);
                 } 
                 else if (data[2].equals(disconnect)) 
                 {
                     //tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                     //userRemove(data[0]);
                 } 
                 else if (data[2].equals(chat)) 
                 {
                     //tellEveryone(message);
                 } 
                 else 
                 {
                     //ta_chat.append("No Conditions were met. \n");
                 }
             } */
          } 
          catch (Exception ex) 
          {
             //ta_chat.append("Lost a connection. \n");
             ex.printStackTrace();
             //clientOutputStreams.remove(client);
          } 
	} 
}
