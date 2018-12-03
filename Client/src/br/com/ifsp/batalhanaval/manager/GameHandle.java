package br.com.ifsp.batalhanaval.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import br.com.ifsp.batalhanaval.gameobjects.Player;
import br.com.ifsp.batalhanaval.gameobjects.Ship;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameHandle implements Runnable{

    BufferedReader hearEnemy;
	Stage stage;
    
    public GameHandle(Stage stage, Socket socket) {
    	this.stage = stage;
    	
        InputStreamReader isReader;
		try {
			isReader = new InputStreamReader(socket.getInputStream());
	        hearEnemy = new BufferedReader(isReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat", hit = "Hit", ready = "Ready" ;
		String[] data;
		
		 try 
		 {
			 while ((message = hearEnemy.readLine()) != null) {
				 
				 System.out.println("RECEBI CLIENTE");
				 data = message.split(":");
				 if(data[0].equals(connect)) {
					 System.out.println("CONECTADOS");
				 }else if(data[0].equals(hit)){
					 int i = Integer.valueOf(data[1]);
					 int j = Integer.valueOf(data[2]);
					 GameManager.getInstance().hit(i, j);
				 }else if(data[0].equals(ready)){
					 Player enemy = GameManager.getInstance().getEnemy();
					 for(int i = 1; i < data.length; i++) {
						String[] infoShip = data[i].split("-");
						System.out.println(infoShip[0]);
						Ship ship = enemy.getShips()[Integer.valueOf(infoShip[0])-1];
						System.out.println(infoShip[1]);
						ship.setHorizontal(Boolean.valueOf(infoShip[1]));
						System.out.println(infoShip[2]);
						ship.setFistPositionRow(Integer.valueOf(infoShip[2]));
						System.out.println(infoShip[3]);
						ship.setFistPositionColumn(Integer.valueOf(infoShip[3]));
						
					 }
					 GameManager.getInstance().configureBoardEnemy();
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
