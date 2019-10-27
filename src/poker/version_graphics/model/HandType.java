package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;
    
	//Boolean für die höchste Strasse (Royalflush)
	private static boolean hstraight;
	
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        if (isRoyalFlush(cards)) currentEval = RoyalFlush;
        
        return currentEval;
    }
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    //Analog zu Paar mit einer zusätzlichen Schleife
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) {
                	for (int k = j+1; k < cards.size() && !found; k++) {
                		if (cards.get(j).getRank() == cards.get(k).getRank()) found = true;
                	}	
                }
            }
        }
        return found;
    }
    
    //Fünf Karten in der Reihe
    public static boolean isStraight(ArrayList<Card> cards) {
        boolean found = false;
        
        hstraight = false;
        
        //Arraylist definieren
        ArrayList<Integer> tempListe = new ArrayList<Integer>();
        
        //Arraylist füllen mit den Werten den Karten
       for (int i = 0; i < cards.size(); i++) {
    	   Card tempCard = cards.get(i);
    	   Integer intTemp = tempCard.getCardOrdinal();
    	   tempListe.add(intTemp);
       }
       
        
        //Arraylist sortieren
       Collections.sort(tempListe);
        
        //die Auswertung normaler Fall
        if (tempListe.get(0)+1 == tempListe.get(1)){
        	if (tempListe.get(1)+1 == tempListe.get(2)) {
        		if (tempListe.get(2)+1 == tempListe.get(3)) {
        			//inkl. Fall Ass als eins!
        			if (tempListe.get(3)+1 == tempListe.get(4) || (tempListe.get(3)+9 == tempListe.get(4))) {
        					found = true;
        					//höchste Strasse
        					if (tempListe.get(4) == 12) {
        						hstraight = true;
        					}
        					
        					return found;
        					
        				
        			}
        		}
        	}
       }

		return found;
    }	
    
    // Wenn alle Karten die gleiche Suit haben => Flush
    public static boolean isFlush(ArrayList<Card> cards) {
        boolean found = false;

        if (cards.get(0).getSuit() == cards.get(1).getSuit() && 
        		cards.get(0).getSuit() == cards.get(2).getSuit()&& 
        		cards.get(0).getSuit() == cards.get(3).getSuit() && 
        		cards.get(0).getSuit() == cards.get(4).getSuit() 
        		) found = true;
            
        return found;
    }
    
    // Wenn ein Dringling und zwei Pair gefunden werden und es kein Vierling ist => FullHouse
    public static boolean isFullHouse(ArrayList<Card> cards) {

           return isThreeOfAKind(cards) && isTwoPair(cards) && !isFourOfAKind(cards);
    }
    
    
    //Analog zu Drilling mit einer zusätzlichen Schleife
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) {
                	for (int k = j+1; k < cards.size() && !found; k++) {
                		if (cards.get(j).getRank() == cards.get(k).getRank()) {
                			for (int m = k+1; m < cards.size() && !found; m++) {
                				 if (cards.get(i).getRank() == cards.get(m).getRank()) found = true;
                			}
                		}
                	}	
                }
            }
        }
        return found;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
    	
    	//Wenn Flush und Strasse wahr ist => StraightFlush
        return isFlush(cards) && isStraight(cards);
    }
    
    public static boolean isRoyalFlush(ArrayList<Card> cards) {
    	boolean found = false;
		
		if(isFlush(cards) && isStraight(cards) && hstraight) {
			found = true;
		}    	
        return found;
    }
}
