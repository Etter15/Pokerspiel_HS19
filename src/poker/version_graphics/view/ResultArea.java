package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ResultArea extends HBox{
    private Label winner = new Label("Ergebnis");
    private Region spacer = new Region(); // Empty spacer
    public ResultArea() {
    	super(); //Super immer zuerst
    	
    	this.getChildren().addAll(winner);

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("resultArea"); // Unique ID in the CSS
    }

    
   public void setWinner(String text) {
	   winner.setText(text);
   }
}
