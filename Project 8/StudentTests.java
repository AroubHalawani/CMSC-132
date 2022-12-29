package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Node;

import frequency.Frequency;
import frequency.WordFrequency;

public class StudentTests {

	@Test
	public void test1() {
		String answer = WordFrequency.freq("data/myTest1.txt",5);
		assertTrue(TestsSupport.isCorrect("data/studentAnswer1.txt", answer));
	}
	
	

	
}
