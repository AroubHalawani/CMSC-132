package blackjack;

import java.util.ArrayList;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {

	// Private instance variables to store the cards
	
	private ArrayList<Card> playerCards;
	private ArrayList<Card> dealerCards;
	private Deck deck;

	// Getter and setter methods
	
	public ArrayList<Card> getDealerCards() {
	
		return new ArrayList<Card>(dealerCards);
	}
	
	public ArrayList<Card> getPlayerCards() {
		
		return new ArrayList<Card>(playerCards);
	}
	
	public void setDealerCards(ArrayList<Card> cards) {
		
		dealerCards=cards;
	}
	
	public void setPlayerCards(ArrayList<Card> cards) {
		
		playerCards=cards;
	}
	
	// Assigning a new instance of the Deck class to the deck variable
	// Shuffling the deck 
	// Passing the parameter 
	
	public void createAndShuffleDeck(Random random) {
		
		Deck newDeck= new Deck();
		
		newDeck.shuffle(random);	
		
		deck=newDeck;				
	}

	// Instantiating the respective list of cards
	// Dealing two cards from the deck and adding them to that list.
	
	public void initialDealerCards() {
		
		this.dealerCards=new ArrayList<Card>();

		dealerCards.add(deck.dealOneCard());
		dealerCards.add(deck.dealOneCard());

	}	

	public void initialPlayerCards() {


		this.playerCards=new ArrayList<Card>();

		playerCards.add(deck.dealOneCard());
		playerCards.add(deck.dealOneCard());

	}

	// Dealing just one card
	// Adding the card to the list
	
	public void playerTakeCard() {

		playerCards.add(deck.dealOneCard());
	}	

	public void dealerTakeCard() {

		dealerCards.add(deck.dealOneCard());
	}
	
	// Evaluating the hand
	// Returning ArrayList contains either one or two Integer
	// If the list is not containing Ace, it will return the sum of the cards
	// If the list is containing Ace, it will return 2 values : the sum and (sum+10)
	
	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {
		
		ArrayList<Integer> answer= new ArrayList<>();
		
		int sum=0;
		int storeSum;
		
		for(Card index : hand) {

			sum+=index.getRank().getValue();				

		}				
		
		answer.add(sum);	

		storeSum=sum;		
		
		for(Card index : hand) {

			if(index.getRank().equals(Rank.ACE)) {

				if((storeSum+10)<=21) {
					
					answer.add((sum+10));
				}

			}
		}

		return answer;		
	}	

	// This method will assess the hand and  return 1 of the 4 HandAssessment constant
	
	public static HandAssessment assessHand(ArrayList<Card> hand) {
		
		if(hand==null||hand.size()<2) {
			
			return HandAssessment.INSUFFICIENT_CARDS;
		
		} else if(hand.size()==2) {
			
			if(possibleHandValues(hand).contains(11)&&possibleHandValues(hand).contains(21)) {
				
				return HandAssessment.NATURAL_BLACKJACK;
			}
			
		} else {
			
			int sum=0;	
			
			for(Card index : hand) {	
				
				sum+=index.getRank().getValue();		
			} 
			
			if(sum>21) {
				
				return HandAssessment.BUST;
			}							
		}	

		return HandAssessment.NORMAL;

	}
	
	// This method will determined the output of the game and who wins
	
	public GameResult gameAssessment() {
		
		int playerSum=0;
		int dealerSum=0;
		
		
		for(Card index : playerCards) {
			
			playerSum+=index.getRank().getValue();
		}
		
		for(Card index : dealerCards) {
			
			dealerSum+=index.getRank().getValue();
		}
				
		if(assessHand(playerCards)==HandAssessment.NATURAL_BLACKJACK&&assessHand(dealerCards)!=HandAssessment.NATURAL_BLACKJACK) {
			
			return GameResult.NATURAL_BLACKJACK;
			
			
		} else if(assessHand(playerCards)==HandAssessment.NATURAL_BLACKJACK&&assessHand(dealerCards)==HandAssessment.NATURAL_BLACKJACK) {
			
			
			return GameResult.PUSH;
			
		} else {
				
			if(assessHand(dealerCards)==HandAssessment.NATURAL_BLACKJACK&& playerSum>21) {
				
				return GameResult.PUSH;
				
			} else if(playerSum>21) {
				
				return GameResult.PLAYER_LOST;
				
			} else if(dealerSum>21) {
				
				return GameResult.PLAYER_WON;
				
			} else if(assessHand(dealerCards)==HandAssessment.NATURAL_BLACKJACK && playerSum==21) {
				
				return GameResult.PUSH;
				
			} else {
				
				if(possibleHandValues(dealerCards).get(0)<possibleHandValues(playerCards).get(0)) {
					
					return GameResult.PLAYER_WON;
					
				} else if(possibleHandValues(dealerCards).get(0)>possibleHandValues(playerCards).get(0)) {
					
					return GameResult.PLAYER_LOST;
					
				} else {
					
					return GameResult.PUSH;
				}

			}
		}					
	}
	
	// This method will determine whether or not the dealer should take another card during her turn or not.
	
	public boolean dealerShouldTakeCard() {

		boolean ace=false;
		
		
		for(Card index : dealerCards) {

			if(index.getRank().equals(Rank.ACE)) {

				ace=true;
				break;
			}
		}
		
			if(possibleHandValues(dealerCards).get(0)<=16) {
				
				return true;
				
			} else if(possibleHandValues(dealerCards).get(0)>=18) {
				
				return false;
				
			} else if(ace) {
				
				if(possibleHandValues(dealerCards).get(0)==7||possibleHandValues(dealerCards).get(0)==17) {
					
					return true;
				}
				
			} else if(possibleHandValues(dealerCards).get(0)==17) {
				
					return false;
				
			}
			
			return false;	
		}		
	}
