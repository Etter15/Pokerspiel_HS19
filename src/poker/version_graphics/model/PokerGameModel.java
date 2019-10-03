package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private String besterSpieler;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(new Player("Player " + i));
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}

	//Gibt den Gewinner des Spiels zurück
	public String returnWinner() {
		
		for (int i = 0; i < (PokerGame.NUM_PLAYERS-1); i++) {
			int j = getPlayer(i).compareTo(getPlayer(i+1));
			System.out.println(j);
			if(j>0) {
				this.besterSpieler = "Der Gewinner ist " +getPlayer(i).getPlayerName();
			}
			if (j<0) {
				this.besterSpieler = "Der Gewinner ist " +getPlayer(i+1).getPlayerName();
			}
			if (j==0) {
				this.besterSpieler = "Gleichstand zwischen " + getPlayer(i).getPlayerName() + " und " + getPlayer(i+1).getPlayerName();
			}			
		}
		
		return besterSpieler;
		
	}
}
