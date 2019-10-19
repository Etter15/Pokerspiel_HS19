package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private String besterSpieler, unentSpieler;
	private int gewinnerArray [];
	private int ergebnisArray [][];
	private boolean unentschieden;

	private int numberOfPlayers;
	
	public PokerGameModel() {		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}

	//Gibt den Gewinner des Spiels zurück 
	
	//Problem wenn player1 gewinnen sollte!!!!!!!!!!!
	public String returnWinner() {
		this.gewinnerArray = new int[this.numberOfPlayers];
		this.ergebnisArray = new int[this.numberOfPlayers][this.numberOfPlayers];
		int gewinner =0, gewinnerIndex=0;
		this.unentSpieler="";
		this.besterSpieler="";
		this.unentschieden = false;
		ArrayList<Integer> unentschiedenSpieler = new ArrayList<>();
		unentschiedenSpieler.clear();
		
		//ErgebnisArray und GewinnerArray befüllen
		for (int i = 0; i < this.ergebnisArray.length; i++) {
			int gewinnZählen = 0;
			
			for (int j = 0; j < this.ergebnisArray[0].length; j++) {
				if ( i==j) {
					
				}else {
					this.ergebnisArray[i][j] = getPlayer(i).compareTo(getPlayer(j));
					if(getPlayer(i).compareTo(getPlayer(j))>0){
						gewinnZählen++;
						}
					if(getPlayer(i).compareTo(getPlayer(j))<0) {
						gewinnZählen--;
						}
					
					}
				}
			
			this.gewinnerArray[i]=gewinnZählen;
			
			}
		
		//Gewinner ermitteln
		for (int i = 0; i < this.gewinnerArray.length; i++) {
			if (this.gewinnerArray[i]> gewinner) {
				gewinner = this.gewinnerArray[i];
				gewinnerIndex=i;
			}
			
		}
		
		//Unentschieden ermitteln
		for (int i = 0; i < this.gewinnerArray.length; i++) {
			if(gewinnerIndex!=i) {
				if(gewinner == this.gewinnerArray[i]) {
					this.unentschieden = true;
					unentschiedenSpieler.add(i);
				}
			}
		}
		
		//Spieler Unentschieden
		if(this.unentschieden) {
			//Spieler welcher als Gewinner eingetragen ist
			this.unentSpieler= this.unentSpieler.concat(gewinnerIndex+" ");
			for (int g=0; g<unentschiedenSpieler.size();g++) {
				this.unentSpieler= this.unentSpieler.concat(unentschiedenSpieler.get(g)+" ");
			}
		}
		
		if(this.unentschieden) {
			this.besterSpieler = "Gleichstand zwischen den Spielern: " + this.unentSpieler ;
		}else {
			this.besterSpieler= "Der Gewinner ist " +getPlayer(gewinnerIndex).getPlayerName();
			}
		return this.besterSpieler;
			
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		
		players.clear();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player("Player " + i));
		}
	}
}	
				


