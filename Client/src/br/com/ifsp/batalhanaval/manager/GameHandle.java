package br.com.ifsp.batalhanaval.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameHandle implements Runnable{

    BufferedReader hearEnemy;
	Stage stage;
    
    public GameHandle(Stage stage) {
    	this.stage = stage;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost", 7777);
			
			String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
	        String[] data;

	        new PrintWriter(socket.getOutputStream()).println(connect);
	        
	         try 
	         {
	        	 while ((message = hearEnemy.readLine()) != null) {
	        		 
	        		 data = message.split(":");
	        		 if(data[0].equals(connect)) {
	        			 System.out.println("CONECTADOS");
        				 stage.show();
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
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
