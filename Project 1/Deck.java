package deckOfCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	
	// Private instance variable
	
	private ArrayList<Card> cards;
	
	// A constructor that instantiates the list of cards, and populate it with the usual 52 cards found in a deck.
	
	public Deck() {
		
		cards = new ArrayList<Card>();
	
		
		for(Suit suit: Suit.values()) {
			
			for(Rank rank: Rank.values()) {
				
				cards.add(new Card(rank,suit));
			}
		}		
	}
	
	// This method will shuffling the deck randomly 
	
	public void shuffle(Random randomNumberGenerator) {
		
		Collections.shuffle(cards, randomNumberGenerator);
	}
	
	// This method will remove one card from the front of the list
	public Card dealOneCard() {
		
		return cards.remove(0);
	}
	
	
}
