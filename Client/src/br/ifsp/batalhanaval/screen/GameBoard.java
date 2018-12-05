package br.ifsp.batalhanaval.screen;

import java.io.IOException;

import br.ifsp.batalhanaval.gameobjects.Player;
import br.ifsp.batalhanaval.gameobjects.Ship;
import br.ifsp.batalhanaval.gameobjects.Tile;
import br.ifsp.batalhanaval.manager.GameManager;
import br.ifsp.batalhanaval.manager.ScreenManager;
import br.ifsp.batalhanaval.manager.GameManager.STATES;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameBoard {
	@FXML
	public Circle circlePlayer, circleEnemy;

	@FXML
	public Pane panePlayer;

	@FXML
	public Label lbMsg, lbPlayer, lbEnemy;

	@FXML
	public ImageView viewPortaAviao, viewEncouracado, viewCruzador, viewSubmarino;

	@FXML
	public GridPane gridPlayer, gridEnemy;

	@FXML
	Button btnReady;

	ImageView hold;
	int holdSize;
	int holdIdShip;

	public void OnClickReady(MouseEvent evt) throws IOException {
		GameManager.getInstance().readyGame();
		btnReady.setDisable(true);
	}

	@FXML
	public void startMenu(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(ScreenManager.MENU));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void hit(int i, int j) {
		try {
			Tile t = (Tile) gridPlayer.getChildren().get(j * 10 + i + 1);
			if (t.getPart() == null)
				t.setFill(Color.AQUA);
			else {
				t.setFill(Color.RED);
				t.getPart().destroyPart();
			}

			if (GameManager.getInstance().isGameEndPlayer()) {
				GameManager.getInstance().changeState(GameManager.STATES.LOSE);
			} else {
				GameManager.getInstance().changeState(GameManager.STATES.YOURTURN);
			}
		} catch (Exception e) {

		}

	}

	public void OnMouseClick(MouseEvent evt) {

		if (hold != null) {
			hold.setOpacity(0.25);
		}

		ImageView typeShip = (ImageView) evt.getSource();
		if (typeShip == viewPortaAviao) {
			holdSize = 5;
			holdIdShip = 1;
		} else if (typeShip == viewEncouracado) {
			holdSize = 4;
			holdIdShip = 2;
		} else if (typeShip == viewCruzador) {
			holdSize = 3;
			holdIdShip = 3;
		} else if (typeShip == viewSubmarino) {
			holdSize = 2;
			holdIdShip = 4;
		}

		hold = (ImageView) evt.getSource();
		hold.setOpacity(1);
	}

	public void showMessageShip(int idShip) {
		switch (idShip) {
		case 1:
			lbMsg.setText("Mensagem: Porta Avião abatido");
			break;
		case 2:
			lbMsg.setText("Mensagem: Encouraçado abatido");
			break;
		case 3:
			lbMsg.setText("Mensagem: Cruzador abatido");
			break;
		case 4:
			lbMsg.setText("Mensagem: Submarino abatido");
			break;
		}
	}

	public void playerReady() {
		lbPlayer.setTextFill(Color.GREEN);
	}

	public void enemyReady() {
		lbEnemy.setTextFill(Color.GREEN);
	}

	public void hideMessageShip() {
		lbMsg.setText("Mensagem:");
	}

	public void verifyReady() {
		if (!viewPortaAviao.isVisible() && !viewEncouracado.isVisible() && !viewCruzador.isVisible()
				&& !viewSubmarino.isVisible()) {

			btnReady.setDisable(false);
		}
	}

	@FXML
	public void initialize() throws IOException {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Tile tilePlayer = new Tile(null);
				Tile tileEnemy = new Tile(null);

				gridPlayer.add(tilePlayer, i, j, 1, 1);
				gridEnemy.add(tileEnemy, i, j, 1, 1);

				EventHandler<InputEvent> handlerTileEnemy = new EventHandler<InputEvent>() {
					public void handle(InputEvent event) {

						try {
							if (GameManager.getInstance().getState() == GameManager.STATES.YOURTURN
									&& GameManager.getInstance().isGameReady()) {
								if (!tileEnemy.getOpen()) {
									int i = gridPlayer.getRowIndex(tileEnemy);
									int j = gridPlayer.getColumnIndex(tileEnemy);
									try {

										GameManager.getInstance().passTurn(i, j);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									if (tileEnemy.getPart() != null) {
										tileEnemy.getPart().destroyPart();
										tileEnemy.setFill(Color.RED);

										GameManager.getInstance().verifyShipDestroyed(tileEnemy.getPart().getIdShip());
										if (GameManager.getInstance().isGameEndEnemy())
											GameManager.getInstance().changeState(GameManager.STATES.WIN);
									} else {
										tileEnemy.setFill(Color.AQUA);
									}

									tileEnemy.setOpen(true);
								}
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};

				EventHandler<InputEvent> handlerClicked = new EventHandler<InputEvent>() {
					public void handle(InputEvent event) {

						if (hold != null) {
							int i = gridPlayer.getRowIndex(tilePlayer);
							int j = gridPlayer.getColumnIndex(tilePlayer);

							int lastPosition = (j + holdSize - 1) * 10 + i + 1;

							// Verifica se pode por o navio na possição
							if (lastPosition <= 100) {

								boolean canPutShip = true;
								for (int offset = 0; offset < holdSize; offset++) {
									Tile t = (Tile) gridPlayer.getChildren().get((j + offset) * 10 + i + 1);
									if (t.getPart() != null) {
										canPutShip = false;
									}
								}

								if (canPutShip) {
									hold.setVisible(false);

									try {
										GameManager.getInstance().setConfigurationShip(holdIdShip, true, i, j);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									Player player;
									for (int offset = 0; offset < holdSize; offset++) {

										Tile t = (Tile) gridPlayer.getChildren().get((j + offset) * 10 + i + 1);
										t.setFill(Color.GREENYELLOW);
										try {
											player = GameManager.getInstance().getPlayer();
											Ship ship = player.getShips()[holdIdShip - 1];
											t.setPart(ship.getParts()[offset]);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}

									verifyReady();

									hold = null;

								}
							}
						}

					}
				};

				tileEnemy.setOnMouseClicked(handlerTileEnemy);
				tilePlayer.setOnMouseClicked(handlerClicked);
			}
		}

		GameManager.getInstance().startGame();

	}

}
