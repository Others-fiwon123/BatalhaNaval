package br.ifsp.batalhanaval.screen;

import br.ifsp.batalhanaval.gameobjects.Board;
import br.ifsp.batalhanaval.gameobjects.Player;
import br.ifsp.batalhanaval.gameobjects.Ship;
import br.ifsp.batalhanaval.gameobjects.Tile;
import br.ifsp.batalhanaval.manager.GameManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameBoard {
	@FXML
	Circle circlePlayer, circleEnemy;

	@FXML
	Pane panePlayer;

	@FXML
	Label lbMsg, lbPlayer, lbEnemy;

	@FXML
	ImageView viewPortaAviao, viewEncouracado, viewCruzador, viewSubmarino;

	@FXML
	GridPane gridPlayer, gridEnemy;

	@FXML
	Button btnReady;

	ImageView hold;
	int holdSize;
	int holdIdShip;

	public Circle getCirclePlayer() {
		return circlePlayer;
	}

	public Circle getCircleEnemy() {
		return circleEnemy;
	}

	public GridPane getGridEnemy() {
		return gridEnemy;
	}

	public void OnClickReady(MouseEvent evt) {
		GameManager.getInstance().readyGame();
		btnReady.setDisable(true);
	}

	private Tile getTileBoard(GridPane board, int i, int j) {
		Board boardPattern = GameManager.getInstance().getBoardPattern();

		int maxTiles = boardPattern.getHeight() * boardPattern.getWidth();
		int index = j * boardPattern.getHeight() + i + 1;

		if (index <= maxTiles)
			return (Tile) gridPlayer.getChildren().get(index);

		return null;
	}

	public void hit(int i, int j) {
		Tile t = getTileBoard(gridPlayer, i, j);

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

	}

	public void OnMouseClickImageView(MouseEvent evt) {

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

	EventHandler<InputEvent> handlerClickBoardEnemy = new EventHandler<InputEvent>() {
		public void handle(InputEvent event) {

			Tile tileEnemy = (Tile) event.getTarget();

			if (GameManager.getInstance().getState() == GameManager.STATES.YOURTURN
					&& GameManager.getInstance().isGameReady()) {
				if (!tileEnemy.getOpen()) {
					int i = gridPlayer.getRowIndex(tileEnemy);
					int j = gridPlayer.getColumnIndex(tileEnemy);
					GameManager.getInstance().passTurn(i, j);

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
		}
	};

	private boolean canPutShip(GridPane board, int i, int j) {

		boolean canPutShip = true;

		Tile lastTile = getTileBoard(board, i, j + holdSize - 1);

		if (lastTile != null) {
			for (int offset = 0; offset < holdSize; offset++) {
				Tile t = getTileBoard(board, i, j + offset);
				if (t.getPart() != null) {
					canPutShip = false;
				}
			}
		} else {
			canPutShip = false;
		}

		return canPutShip;
	}

	EventHandler<InputEvent> handlerClickBoardPlayer = new EventHandler<InputEvent>() {
		public void handle(InputEvent event) {

			Tile tilePlayer = (Tile) event.getTarget();

			if (hold != null) {
				int i = gridPlayer.getRowIndex(tilePlayer);
				int j = gridPlayer.getColumnIndex(tilePlayer);

				if (canPutShip(gridPlayer, i, j)) {
					hold.setVisible(false);

					GameManager.getInstance().setConfigurationShip(holdIdShip, true, i, j);

					Player player = GameManager.getInstance().getPlayer();
					for (int offset = 0; offset < holdSize; offset++) {

						Tile t = getTileBoard(gridPlayer, i, j + offset);
						t.setFill(Color.GREENYELLOW);
						Ship ship = player.getShips()[holdIdShip - 1];
						t.setPart(ship.getParts()[offset]);
					}

					verifyReady();

					hold = null;

				}
			}

		}
	};

	public void initBoard() {
		Board boardPattern = null;

		boardPattern = GameManager.getInstance().getBoardPattern();

		for (int i = 0; i < boardPattern.getHeight(); i++) {
			for (int j = 0; j < boardPattern.getWidth(); j++) {

				Tile tilePlayer = new Tile(null);
				tilePlayer.setOnMouseClicked(handlerClickBoardPlayer);

				Tile tileEnemy = new Tile(null);
				tileEnemy.setOnMouseClicked(handlerClickBoardEnemy);

				gridPlayer.add(tilePlayer, i, j, 1, 1);
				gridEnemy.add(tileEnemy, i, j, 1, 1);

			}
		}
	}

	public void initialize() {
		initBoard();
		GameManager.getInstance().startGame();

	}

}
