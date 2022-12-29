package tests;
import deckOfCards.*;
import blackjack.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Test;
public class StudentTest {
	
	@Test
	
	public void test1()
	{
		ArrayList<Card> playerCard= new ArrayList<>();
		
		ArrayList<Integer> dealerCard= new ArrayList<>();
		
		playerCard.add(new Card(Rank.ACE,Suit.CLUBS));
		
		playerCard.add(new Card(Rank.KING,Suit.DIAMONDS));
		
		dealerCard=BlackjackModel.possibleHandValues(playerCard);
		
		assertTrue(dealerCard.get(0).equals(11));
		
		assertTrue(dealerCard.get(1).equals(21));
		
		System.out.print(playerCard);
		
		
	}

	@Test
	
	public void test2()
	{
		
	}
	
	
	

}
