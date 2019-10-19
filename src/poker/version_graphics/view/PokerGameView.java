package poker.version_graphics.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

import javafx.animation.*;
import javafx.util.Duration;

public class PokerGameView {
	private StackPane main;
	private Label message;
	private FadeTransition transition;
	private HBox players;
	private VBox SpielerFeldLinks, SpielerFeldRechts;
	private ControlArea controls;
	private ResultArea results;

	private PokerGameModel model;

	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;

		// Create all of the player panes we need, and put them into an HBox
		// Erweitert damit es zwei Reihen gibt
		SpielerFeldLinks = new VBox();
		SpielerFeldRechts = new VBox();

		main = new StackPane();

		message = new Label("");
		message.setId("message");
		message.setOpacity(0);
		main.getChildren().add(message);
		

		players = new HBox();
		players.getChildren().addAll(SpielerFeldLinks, SpielerFeldRechts);
		main.getChildren().add(players);


		if (main.getChildren().size() > 1) {
		   // Top Component
		   Node topNode = main.getChildren().get(main.getChildren().size()-1);
		   topNode.toBack();
		}
		
		main.getChildren().get(1).toFront();
		main.getChildren().get(0).toBack();

		for (int i = 0; i < model.getNumberOfPlayers(); i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			if (i == 0 || i % 2 == 0) {
				SpielerFeldLinks.getChildren().add(pp);
			} else {
				SpielerFeldRechts.getChildren().add(pp);
			}
		}

		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic

		// Erstelle Results area für den Gewinner
		results = new ResultArea();

		// Put players and controls and results into a BorderPane
		BorderPane root = new BorderPane();
		root.setCenter(main);
		root.setBottom(controls);
		root.setTop(results);

		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

		// Create the scene using our layout; then display it
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
		stage.setTitle("Poker Miniproject");
		stage.setScene(scene);
		stage.show();
	}

	public PlayerPane getPlayerPane(int i) {
		// get left or right field
		VBox Spielfeld = (VBox) players.getChildren().get(i % 2);
		return (PlayerPane) Spielfeld.getChildren().get(i / 2);
	}

	public Button getShuffleButton() {
		return controls.btnShuffle;
	}

	public Button getDealButton() {
		return controls.btnDeal;
	}

	public void updateResult(String text) {
		results.setWinner(text);
		message.setText(text);
		
		if( transition == null ) {
			transition = new FadeTransition(Duration.millis(2000), message);
			transition.setFromValue(1.0);
			transition.setToValue(0);
			transition.setDelay(Duration.millis(2000));
		}
		
		transition.stop();
		message.setOpacity(1);
		transition.play();

	}
}
