package poker.version_graphics.view;

import java.awt.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

public class StartGameView {
	private VBox root;
	private Label lblInstruction;
	private TextField txtNumberOfPlayers;
	private Button btnStart;
	private Stage stage;

	public Button getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(Button btnStart) {
		this.btnStart = btnStart;
	}

	private PokerGameModel model;

	public StartGameView(Stage stage, PokerGameModel model) {
		this.model = model;

		root = new VBox();
		lblInstruction = new Label("Wählen Sie die Spieleranzahl");
		txtNumberOfPlayers = new TextField();
		btnStart = new Button("Start");
		btnStart.setDisable(true);
		root.getChildren().addAll(lblInstruction, txtNumberOfPlayers, btnStart);

		txtNumberOfPlayers.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				btnStart.setDisable(true);
				if (!newValue.matches("\\d*") || txtNumberOfPlayers.getText().length() > 2) {
					txtNumberOfPlayers.setText(oldValue);
				}
				int i = 0;
				try {
					i = Integer.parseInt(txtNumberOfPlayers.getText());
				} catch (NumberFormatException e) {
					txtNumberOfPlayers.setText("");
				}
				if( i < 2 || i > 10 ) {
					txtNumberOfPlayers.getStyleClass().remove("number-of-players-green");
					txtNumberOfPlayers.getStyleClass().add("number-of-players-red");
				}else {
					txtNumberOfPlayers.getStyleClass().remove("number-of-players-red");
					txtNumberOfPlayers.getStyleClass().add("number-of-players-green");
					btnStart.setDisable(false);
				}
			}
		});

		// Disallow resizing
		stage.setResizable(false);

		// Create the scene using our layout; then display it
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
		stage.setTitle("Poker Miniproject");
		stage.setScene(scene);
		stage.show();
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
