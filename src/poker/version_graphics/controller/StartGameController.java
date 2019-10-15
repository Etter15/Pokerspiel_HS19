package poker.version_graphics.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;
import poker.version_graphics.view.StartGameView;

public class StartGameController {
	private PokerGameModel model;
	private StartGameView view;

	public StartGameController(PokerGameModel model, StartGameView view) {
		this.model = model;
		this.view = view;

		view.getBtnStart().setOnAction(e -> start());
	}

	private void start() {
		PokerGameView pokerView;
		PokerGameController pokerController;

		int numberOfPlayers;
		try {
			numberOfPlayers = Integer.parseInt(view.getTxtNumberOfPlayers().getText());
		} catch (NumberFormatException e) {
//TODO show error message
			numberOfPlayers = 2;
		}
		model.setNumberOfPlayers(numberOfPlayers);

		pokerView = new PokerGameView(view.getStage(), model);
		pokerController = new PokerGameController(model, pokerView);
	}

}
