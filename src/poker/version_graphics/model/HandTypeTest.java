package poker.version_graphics.model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HandTypeTest {
	// We define the hands using abbreviations. The code at the bottom
	// of this class can translate one of these strings into a card.
	//
	// Another method takes a set of five cards, and translates the whole hand
	//
	// Yet another method does this for a whole set of hands
	private static String[][] highCards = {
			{ "2S", "9C", "3H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "2S", "3S", "4S", "5S", "7S" },
			{ "AS", "KC", "QH", "JD", "TH" }
			};
	
	private static String[][] pairs = {
			{ "2S", "2C", "3H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "3S", "2C", "3H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7H", "5D", "7H" },
			{ "2S", "AC", "5H", "5D", "AH" },
			{ "3S", "2C", "3H", "2D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }
			};
	
	private static String[][] triple = {
			{ "2S", "2C", "2H", "5D", "7D" },
			{ "2S", "2C", "7D", "5D", "2H" },
			{ "2S", "7D", "2H", "5D", "2C" },
			{ "7D", "5D", "2H", "2C", "2S" }
			};
	
	private static String[][] quadruple = {
			{ "2S", "2C", "2H", "2D", "7D" },
			{ "2S", "2C", "7D", "2D", "2H" },
			{ "2S", "7D", "2H", "2D", "2C" },
			{ "7D", "2D", "2H", "2C", "2S" }
			};
	
	private static String[][] straight = {
			{ "3S", "6D", "4D", "5H", "2C" },
			{ "4C", "2H", "AS", "5D", "3H" },
			{ "KH", "AC", "QC", "JS", "TD" },
			{ "8D", "9S", "TH", "JC", "QS" }
			};
	
	private static String[][] flush = {
			{ "3S", "7S", "KS", "2S", "TS" },
			{ "KC", "JC", "2C", "4C", "9C" },
			{ "AH", "QH", "3H", "5H", "8H" },
			{ "3H", "2H", "KH", "AH", "6H" }
			};
	
	private static String[][] fullHouse = {
			{ "3S", "2D", "3H", "2D", "3S" },
			{ "7H", "6C", "6H", "6C", "7C" },
			{ "7S", "7C", "JH", "7S", "JH" },
			{ "AS", "AC", "AD", "KD", "KD" }
			};
	
	private static String[][] straightFlush = {
			{ "9H", "TH", "KH", "QH", "JH" },
			{ "3C", "AC", "5C", "2C", "4C" },
			{ "9S", "JS", "8S", "TS", "QS" },
			{ "6D", "2D", "3D", "4D", "5D" }
			};
	
	private static String[][] royalFlush = {
			{ "AH", "TH", "KH", "QH", "JH" },
			{ "KC", "AC", "TC", "QC", "JC" },
			{ "JS", "KS", "AS", "QS", "TS" },
			{ "TD", "JD", "QD", "AD", "KD" }
			};
	
	// This is where we store the translated hands
	ArrayList<ArrayList<Card>> highCardHands;
	ArrayList<ArrayList<Card>> pairHands;
	ArrayList<ArrayList<Card>> twoPairHands;
	ArrayList<ArrayList<Card>> tripleHands;
	ArrayList<ArrayList<Card>> quadrupleHands;
	ArrayList<ArrayList<Card>> straightHands;
	ArrayList<ArrayList<Card>> flushHands;
	ArrayList<ArrayList<Card>> fullHouseHands;
	ArrayList<ArrayList<Card>> straightFlushHands;
	ArrayList<ArrayList<Card>> royalFlushHands;

	
	/**
	 * The makeHands method is called before each test method,
	 * and prepares the translated hands. We recreate these for
	 * each test method, in case the test method damages the data.
	 */
	@Before
	public void makeHands() {
		highCardHands = makeHands(highCards);
		pairHands = makeHands(pairs);
		twoPairHands = makeHands(twoPairs);
		tripleHands = makeHands(triple);
		quadrupleHands = makeHands(quadruple);
		straightHands = makeHands(straight);
		flushHands = makeHands(flush);
		fullHouseHands = makeHands(fullHouse);
		straightFlushHands = makeHands(straightFlush);
		royalFlushHands = makeHands(royalFlush);
	}

	/**
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.
	 */
	@Test
	public void testIsOnePair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isOnePair(hand)); // Two-pair contains a pair
		}
		for (ArrayList<Card> hand : tripleHands) {
			assertTrue(HandType.isOnePair(hand)); // triple contains a pair
		}
		for (ArrayList<Card> hand : quadrupleHands) {
			assertTrue(HandType.isOnePair(hand)); // Quadruple contains a pair
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isOnePair(hand)); // Straight can not contain pair
		} 
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isOnePair(hand)); // Flush can not contain pair
		} // this check is only valid when played with one Deck (no duplicated cards)
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isOnePair(hand)); //Full House contains pair
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isOnePair(hand)); // Straight Flush can not contain pair
		} 
		for (ArrayList<Card> hand : royalFlushHands) {
			assertFalse(HandType.isOnePair(hand)); // Royal Flush can not contain pair
		} // this check is only valid when played with one Deck (no duplicated cards)
	}

	/**
	 * This is the test method for the isTwoPair in HandType.
	 */
	@Test
	public void testIsTwoPair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : tripleHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
	}
	
	/**
	 * This is the test method for the isThreeOfAKind in HandType.
	 */
	@Test
	public void testIstriple() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : tripleHands) {
			assertTrue(HandType.isThreeOfAKind(hand));
		}
	}
	
	/**
	 * This is the test method for the isFourOfAKind in HandType.
	 */
	@Test
	public void testIsFourOfAKind() {
		for (ArrayList<Card> hand : quadrupleHands) {
			assertTrue(HandType.isFourOfAKind(hand));
		}
	}
	
	/**
	 * This is the test method for the isStraight in HandType.
	 */
	@Test
	public void testIsStraight() {
		for (ArrayList<Card> hand : straightHands) {
			assertTrue(HandType.isStraight(hand));
		}
	}
	
	/**
	 * This is the test method for the isFlush in HandType.
	 */
	@Test
	public void testIsFlush() {
		for (ArrayList<Card> hand : flushHands) {
			assertTrue(HandType.isFlush(hand));
		}
	}

	/**
	 * This is the test method for the isFullHouse in HandType.
	 */
	@Test
	public void testIsFullHouse() {
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isFullHouse(hand));
		}
	}
	
	/**
	 * This is the test method for the isStraightFlush in HandType.
	 */
	@Test
	public void testIsStraightFlush() {
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isStraightFlush(hand));
		}
	}
	
	/**
	 * This is the test method for the isRoyalFlush in HandType.
	 */
	@Test
	public void testIsRoyalFlush() {
		for (ArrayList<Card> hand : royalFlushHands) {
			assertTrue(HandType.isRoyalFlush(hand));
		}
	}
	
	/**
	 * Make an ArrayList of hands from an array of string-arrays
	 */
	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
		for (String[] hand : handsIn) {
			handsOut.add(makeHand(hand));
		}
		return handsOut;
	}
	
	/**
	 * Make a hand (ArrayList<Card>) from an array of 5 strings
	 */
	private ArrayList<Card> makeHand(String[] inStrings) {
		ArrayList<Card> hand = new ArrayList<>();
		for (String in : inStrings) {
			hand.add(makeCard(in));
		}
		return hand;
	}
	
	/**
	 * Create a card from a 2-character String.
	 * First character is the rank (2-9, T, J, Q, K, A) 
	 * Second character is the suit (C, D, H, S)
	 * 
	 * No validation or error handling!
	 */
	private Card makeCard(String in) {
		char r = in.charAt(0);
		Card.Rank rank = null;
		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
		else if (r == 'T') rank = Card.Rank.Ten;
		else if (r == 'J') rank = Card.Rank.Jack;
		else if (r == 'Q') rank = Card.Rank.Queen;
		else if (r == 'K') rank = Card.Rank.King;
		else if (r == 'A') rank = Card.Rank.Ace;
		
		char s = in.charAt(1);
		Card.Suit suit = null;
		if (s == 'C') suit = Card.Suit.Clubs;
		if (s == 'D') suit = Card.Suit.Diamonds;
		if (s == 'H') suit = Card.Suit.Hearts;
		if (s == 'S') suit = Card.Suit.Spades;

		return new Card(suit, rank);
	}
}
