package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private String besterSpieler, unentSpieler="";
	private int gleichstand=0, gewinner=0, tempunentschieden=0; 
	private int numberOfPlayers;
	
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
	
	//Problem wenn player1 gewinnen sollte!!!!!!!!!!!
	public String returnWinner() {
		this.unentSpieler=""; //String zurücksetzen
		
			// 2 Spielerfall
			/*int j = getPlayer(0).compareTo(getPlayer(1));
			
			if(j>0) {
				this.besterSpieler = "Der Gewinner ist " +getPlayer(0).getPlayerName();
			}
			if (j<0) {
				this.besterSpieler = "Der Gewinner ist " +getPlayer(1).getPlayerName();
			}
			if (j==0) {
				this.besterSpieler = "Gleichstand zwischen " + getPlayer(0).getPlayerName() + " und " + getPlayer(1).getPlayerName();
			}
			*/
		
			//Erweiterung mehr als 2 Spieler
			
			for (int i = (PokerGame.NUM_PLAYERS-1); i >= 0; i--) {
				int tempgewinner=1000;// zeigt ob der i-Spieler der Gewinner ist
				if (i==(PokerGame.NUM_PLAYERS-1)) {
					this.tempunentschieden=0; // in der aller ersten Runde setzen, damit Vorrunde keinen Einfluss
					System.out.println(this.tempunentschieden);
				}
				for (int j = 0; j < (PokerGame.NUM_PLAYERS); j++ ) {
					
					if((i>0 && j==0)||(i==0 & j==1)  ) { //im ersten Durchlauf eines Spieler muss es möglich sein in die Gewinnerschleife zu kommen
					tempgewinner=i;
					}
					
					if(i==j) {
					}else{
						System.out.println("Runde "+i + " " + j);
						System.out.println("Rundetemp "+tempgewinner);
						
						int m = getPlayer(i).compareTo(getPlayer(j));
							if(m>0 && tempgewinner == i) {
								tempgewinner = i;
								tempunentschieden = 0;
								System.out.println("temp"+tempgewinner + "unent" + tempunentschieden);
								}
							
							/*
							 * 
							 * Hier hat es einen Bug, es bleibt immer ein Fall übrig, der nicht stimmt!
							 * Nur bei >2 Spielern ein Problem
							 */
							
						
							if (m<0) {
								tempgewinner = 1000;
								if(i==0) {//Stellt sicher, dass höhere vorhergehende Unentschieden nicht auf 0 gesetzt
									if(j==(PokerGame.NUM_PLAYERS-1)) {
										tempunentschieden = 0;
									}
								}else {
									tempunentschieden = 0; 
								}
								
								
								System.out.println("looser"+tempgewinner);
								}
							
							
							/*
							 * 
							 * 
							 */
							
							if (m==0) {
								if (tempgewinner==i) {
									tempunentschieden=1;
									tempgewinner=500;
									this.unentSpieler= this.unentSpieler.concat(i+" ");
									}
								if(tempgewinner == 500) {
									tempunentschieden=1;
								}
								if (tempgewinner == 1000) {
									tempunentschieden=0;
								}

							}
					}
					
				}
				
				System.out.println("Unentschieden"+this.tempunentschieden);
				if(tempgewinner == i) {
					this.gewinner = tempgewinner;
					System.out.println("Gewinner"+this.gewinner);
					}
			
			}
			
			if (this.gewinner >=0 && this.gewinner< PokerGame.NUM_PLAYERS) {
				if(tempunentschieden == 1) {
					this.besterSpieler = "Gleichstand zwischen den Spielern: " + this.unentSpieler ;
				}else {
					this.besterSpieler = "Der Gewinner ist " +getPlayer(this.gewinner).getPlayerName();
				}	
			}else {
				this.besterSpieler = "Gleichstand zwischen den Spielern: " + this.unentSpieler ;
				}
			
		return besterSpieler;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
}	
				


