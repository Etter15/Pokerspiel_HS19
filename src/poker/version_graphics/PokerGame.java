package poker.version_graphics;

import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.controller.StartGameController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PokerGameView;
import poker.version_graphics.view.StartGameView;

public class PokerGame extends Application {
	public static final int NUM_PLAYERS = 3;
	PokerGameModel model;
	
	
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	StartGameView startView;
    	StartGameController startController;
    	
    	// Create and initialize the MVC components
    	model = new PokerGameModel();
    	startView = new StartGameView(primaryStage, model);
    	startController = new StartGameController(model, startView);
    }
    
}
