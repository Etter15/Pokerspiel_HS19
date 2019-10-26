package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
    }
    
    public int getNumCards() {
        return cards.size();
    }

    /**
     * If the hand has not been evaluated, but does have all cards, 
     * then evaluate it.
     */
    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
    	int ergebnis = handType.compareTo(o.handType);
    		if (ergebnis == 0) {
    			int i = handType.ordinal();
				switch(i){
					//highcard vollständig für alle Karten
					case(0):int höchsteKarte=0;
							int höchsteKarteGegner=0;
							//Spieler
					
							//Arraylist definieren
							ArrayList<Integer> tempListeA = new ArrayList<Integer>();
	        
							//Arraylist füllen mit den Werten den Karten
							for (int d = 0; d < cards.size(); d++) {
								Card tempCard = cards.get(d);
								Integer intTemp = tempCard.getCardOrdinal();
								tempListeA.add(intTemp);
							}
	       
							//Arraylist sortieren
							Collections.sort(tempListeA);
							höchsteKarte = tempListeA.get(4);
					
					
							//Gegner
							//Arraylist definieren
							ArrayList<Integer> tempListeB = new ArrayList<Integer>();
	        
							//Arraylist füllen mit den Werten den Karten
							for (int e = 0; e < o.cards.size(); e++) {
								Card tempCard = o.cards.get(e);
								Integer intTemp = tempCard.getCardOrdinal();
								tempListeB.add(intTemp);
							}
	       
							//Arraylist sortieren
							Collections.sort(tempListeB);
							höchsteKarteGegner = tempListeB.get(4);
					
					if(höchsteKarte>höchsteKarteGegner) {
						ergebnis=1;
					}
					if(höchsteKarte<höchsteKarteGegner) {
						ergebnis=-1;
						}	
					if(höchsteKarte==höchsteKarteGegner) {
						höchsteKarte=tempListeA.get(3);
						höchsteKarteGegner=tempListeB.get(3);
						if(höchsteKarte>höchsteKarteGegner) {
							ergebnis=1;
						}
						if(höchsteKarte<höchsteKarteGegner) {
							ergebnis=-1;
							}	
						if(höchsteKarte==höchsteKarteGegner) {
							höchsteKarte=tempListeA.get(2);
							höchsteKarteGegner=tempListeB.get(2);
							if(höchsteKarte>höchsteKarteGegner) {
								ergebnis=1;
							}
							if(höchsteKarte<höchsteKarteGegner) {
								ergebnis=-1;
								}	
							if(höchsteKarte==höchsteKarteGegner) {
								höchsteKarte=tempListeA.get(1);
								höchsteKarteGegner=tempListeB.get(1);
								if(höchsteKarte>höchsteKarteGegner) {
									ergebnis=1;
								}
								if(höchsteKarte<höchsteKarteGegner) {
									ergebnis=-1;
									}	
								if(höchsteKarte==höchsteKarteGegner) {
									höchsteKarte=tempListeA.get(0);
									höchsteKarteGegner=tempListeB.get(0);
									if(höchsteKarte>höchsteKarteGegner) {
										ergebnis=1;
									}
									if(höchsteKarte<höchsteKarteGegner) {
										ergebnis=-1;
										}	
								}
							}
						}
					}
					break;
					//pair nur welches Paar höher
					case(1):int höchstesPaar=0;
							int höchstesPaarGegner=0;
							//Spieler
							for (int a = 0; a < cards.size(); a++) {
								for (int j = i+1; j < cards.size(); j++) {
									if (cards.get(a).getRank() == cards.get(j).getRank()) 
										höchstesPaar=cards.get(a).getCardOrdinal();
								}
							}
							//Gegner
							for (int a = 0; a < o.cards.size(); a++) {
								for (int j = a+1; j < o.cards.size(); j++) {
									if (o.cards.get(a).getRank() == o.cards.get(j).getRank()) 
										höchstesPaarGegner=o.cards.get(a).getCardOrdinal();
								}
							}
							if(höchstesPaar>höchstesPaarGegner) {
								ergebnis=1;
							}
							if(höchstesPaar<höchstesPaarGegner) {
								ergebnis=-1;
							}	
							
							break;
					// twoPair fehlt
					case(2):int höchstestwoPaar=0;
							int höchstestwoPaarGegner=0;

					//ThreeOfAKind muss nur ein Paar gesucht werden, ohne weitere Karten
					case(3):int höchster3 =0;
							int höchster3Gegner =0;
							//Spieler 
							for (int a = 0; a < cards.size(); a++) {
								for (int j = i+1; j < cards.size(); j++) {
									if (cards.get(a).getRank() == cards.get(j).getRank()) 
										höchster3=cards.get(a).getCardOrdinal();
								}
							}
							//Gegner
							for (int a = 0; a < o.cards.size(); a++) {
								for (int j = a+1; j < o.cards.size(); j++) {
									if (o.cards.get(a).getRank() == o.cards.get(j).getRank()) 
										höchster3Gegner=o.cards.get(a).getCardOrdinal();
								}
							}
							if(höchster3>höchster3Gegner) {
								ergebnis=1;
							}
							if(höchster3<höchster3Gegner) {
								ergebnis=-1;
							}	
							break;
					//Straight (vollständig)
					case(4):int höchsteStrasse=0;
							int höchsteStrasseGegner=0;
							//Spieler
							
								//Arraylist definieren
								ArrayList<Integer> tempListe = new ArrayList<Integer>();
			        
								//Arraylist füllen mit den Werten den Karten
								for (int d = 0; d < cards.size(); d++) {
									Card tempCard = cards.get(d);
									Integer intTemp = tempCard.getCardOrdinal();
									tempListe.add(intTemp);
								}
			       
								//Arraylist sortieren
								Collections.sort(tempListe);
								höchsteStrasse = tempListe.get(4);
							
							
							//Gegner
								//Arraylist definieren
								ArrayList<Integer> tempListeG = new ArrayList<Integer>();
			        
								//Arraylist füllen mit den Werten den Karten
								for (int e = 0; e < o.cards.size(); e++) {
									Card tempCard = o.cards.get(e);
									Integer intTemp = tempCard.getCardOrdinal();
									tempListeG.add(intTemp);
								}
			       
								//Arraylist sortieren
								Collections.sort(tempListeG);
								höchsteStrasseGegner = tempListeG.get(4);
							
							if(höchsteStrasse>höchsteStrasseGegner) {
								ergebnis=1;
							}
							if(höchsteStrasse<höchsteStrasseGegner) {
								ergebnis=-1;
								}	
							break;
					//Flush nur Highcard suchen (vollständig)
					case(5):höchsteKarte=0;
							höchsteKarteGegner=0;
							//Spieler
			
							//Arraylist definieren
							ArrayList<Integer> tempListeE = new ArrayList<Integer>();
    
							//Arraylist füllen mit den Werten den Karten
							for (int d = 0; d < cards.size(); d++) {
								Card tempCard = cards.get(d);
								Integer intTemp = tempCard.getCardOrdinal();
								tempListeE.add(intTemp);
							}
   
							//Arraylist sortieren
							Collections.sort(tempListeE);
							höchsteKarte = tempListeE.get(4);
			
			
							//Gegner
							//Arraylist definieren
							ArrayList<Integer> tempListeF = new ArrayList<Integer>();
    
							//Arraylist füllen mit den Werten den Karten
							for (int e = 0; e < o.cards.size(); e++) {
								Card tempCard = o.cards.get(e);
								Integer intTemp = tempCard.getCardOrdinal();
								tempListeF.add(intTemp);
							}
   
							//Arraylist sortieren
							Collections.sort(tempListeF);
							höchsteKarteGegner = tempListeF.get(4);
			
							if(höchsteKarte>höchsteKarteGegner) {
								ergebnis=1;
							}
							if(höchsteKarte<höchsteKarteGegner) {
								ergebnis=-1;
							}	
							if(höchsteKarte==höchsteKarteGegner) {
								höchsteKarte=tempListeE.get(3);
								höchsteKarteGegner=tempListeF.get(3);
								if(höchsteKarte>höchsteKarteGegner) {
									ergebnis=1;
								}
								if(höchsteKarte<höchsteKarteGegner) {
									ergebnis=-1;
								}	
								if(höchsteKarte==höchsteKarteGegner) {
									höchsteKarte=tempListeE.get(2);
									höchsteKarteGegner=tempListeF.get(2);
									if(höchsteKarte>höchsteKarteGegner) {
										ergebnis=1;
									}
									if(höchsteKarte<höchsteKarteGegner) {
										ergebnis=-1;
									}	
									if(höchsteKarte==höchsteKarteGegner) {
										höchsteKarte=tempListeE.get(1);
										höchsteKarteGegner=tempListeF.get(1);
										if(höchsteKarte>höchsteKarteGegner) {
											ergebnis=1;
										}
										if(höchsteKarte<höchsteKarteGegner) {
											ergebnis=-1;
										}	
										if(höchsteKarte==höchsteKarteGegner) {
											höchsteKarte=tempListeE.get(0);
											höchsteKarteGegner=tempListeF.get(0);
											if(höchsteKarte>höchsteKarteGegner) {
												ergebnis=1;
											}
											if(höchsteKarte<höchsteKarteGegner) {
												ergebnis=-1;
								}	
						}
					}
				}
			}
			break;
					//Fullhouse fehlt
					case(6):
							break;
					//FourOfAKind analog Drilling, ohne weitere Highcards.
					case(7):höchster3 =0;
							höchster3Gegner =0;
							//Spieler 
							for (int a = 0; a < cards.size(); a++) {
								for (int j = i+1; j < cards.size(); j++) {
									if (cards.get(a).getRank() == cards.get(j).getRank()) 
										höchster3=cards.get(a).getCardOrdinal();
								}
							}
							//Gegner
							for (int a = 0; a < o.cards.size(); a++) {
								for (int j = a+1; j < o.cards.size(); j++) {
									if (o.cards.get(a).getRank() == o.cards.get(j).getRank()) 
										höchster3Gegner=o.cards.get(a).getCardOrdinal();
								}
							}
							if(höchster3>höchster3Gegner) {
								ergebnis=1;
							}
							if(höchster3<höchster3Gegner) {
								ergebnis=-1;
							}	
							break;
					//StraightFlush wie Strasse (vollständig)
					case(8):höchsteStrasse=0;
							höchsteStrasseGegner=0;
							//Spieler
					
							//Arraylist definieren
							ArrayList<Integer> tempListeM = new ArrayList<Integer>();
	        
							//Arraylist füllen mit den Werten den Karten
							for (int d = 0; d < cards.size(); d++) {
								Card tempCard = cards.get(d);
								Integer intTemp = tempCard.getCardOrdinal();
								tempListeM.add(intTemp);
							}
	       
							//Arraylist sortieren
							Collections.sort(tempListeM);
							höchsteStrasse = tempListeM.get(4);
					
					
							//Gegner
							//Arraylist definieren
							ArrayList<Integer> tempListeN = new ArrayList<Integer>();
	        
							//Arraylist füllen mit den Werten den Karten
							for (int e = 0; e < o.cards.size(); e++) {
								Card tempCard = o.cards.get(e);
								Integer intTemp = tempCard.getCardOrdinal();
								tempListeN.add(intTemp);
							}
	       
						//Arraylist sortieren
							Collections.sort(tempListeN);
							höchsteStrasseGegner = tempListeN.get(4);
					
							if(höchsteStrasse>höchsteStrasseGegner) {
								ergebnis=1;
							}
							if(höchsteStrasse<höchsteStrasseGegner) {
								ergebnis=-1;
							}	
					break;
					//RoyalFlush nicht nötig, immer Unentschieden
					case(9):
							break;
		
					}
				}
        return ergebnis;
    }
}
