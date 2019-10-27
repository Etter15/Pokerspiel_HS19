package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poker.version_graphics.model.Card;
import javafx.scene.transform.Rotate;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class CardLabel extends Label {
	private ImageView imv;

	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}

	public void setCard(Card card) {
		if (card != null) {
			String fileName = cardToFileName(card);
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/" + fileName));
			imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
		} else {
			setGraphic(null);
			return;
		}

		RotateTransition rTransition90 = new RotateTransition();
		rTransition90.setAxis(Rotate.Y_AXIS);
		rTransition90.setDuration(Duration.millis(500));
		rTransition90.setFromAngle(0);
		rTransition90.setToAngle(90);
		rTransition90.setNode(this);
		rTransition90.play();

		rTransition90.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setGraphic(imv);
			}
		});

		RotateTransition rTransition180 = new RotateTransition();
		rTransition180.setAxis(Rotate.Y_AXIS);
		rTransition180.setDuration(Duration.millis(500));
		rTransition180.setDelay(Duration.millis(1000));
		rTransition180.setFromAngle(90);
		rTransition180.setToAngle(0);
		rTransition180.setNode(this);
		rTransition180.play();
	}

	private String cardToFileName(Card card) {
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}

}
