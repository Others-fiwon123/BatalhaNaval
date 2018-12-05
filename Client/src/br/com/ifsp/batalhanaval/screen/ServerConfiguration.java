package br.com.ifsp.batalhanaval.screen;

import java.io.IOException;

import br.com.ifsp.batalhanaval.manager.ScreenManager;
import br.com.ifsp.batalhanaval.manager.ServerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerConfiguration {
	
	@FXML
	TextField tfIp, tfPort;
	
	
	public void saveConfiguration(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ScreenManager.INITSCREEN));
		Parent parent;
		try {
			parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	@FXML
    public void initialize() {
		System.out.println("OI");
		tfIp.setText(ServerManager.getInstance().getIp());
		tfPort.setText(String.valueOf(ServerManager.getInstance().getPort()));
	}
}
