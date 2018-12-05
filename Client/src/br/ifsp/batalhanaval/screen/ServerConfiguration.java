package br.ifsp.batalhanaval.screen;

import br.ifsp.batalhanaval.manager.ScreenManager;
import br.ifsp.batalhanaval.manager.ServerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ServerConfiguration {

	@FXML
	TextField tfIp, tfPort;

	public void saveConfiguration(ActionEvent event) {
		
		ScreenManager.getInstance().setScreen(ScreenManager.INITSCREEN);
		ScreenManager.getInstance().showScreen();
		
	}

	@FXML
	public void initialize() {
		tfIp.setText(ServerManager.getInstance().getIp());
		tfPort.setText(String.valueOf(ServerManager.getInstance().getPort()));
	}
}
