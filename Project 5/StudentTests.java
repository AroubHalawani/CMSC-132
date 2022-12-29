package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClass.BasicLinkedList;

public class StudentTests {
	
	@Test
	public void test1() {
		BasicLinkedList<Integer> blist = new BasicLinkedList<>();
		
		blist.addToFront(6);
		blist.addToFront(4);
		String answer = "";
		for (Integer entry : blist) {
			answer += entry;
		}
		System.out.println(answer);
	}
	@Test
	public void test2() {
		
		BasicLinkedList<String> blist = new BasicLinkedList<String>();
		
		blist.addToFront("Zebra").addToFront("Bear").addToFront("Dove");
		String answer = "";
		for (String entry : blist) {
			answer += entry;
		}
		System.out.println(answer);
		System.out.println();
	}
	@Test
	public void test3() {
		
		BasicLinkedList<String> blist = new BasicLinkedList<String>();
		
		blist.addToFront("Zebra").addToFront("Bear").addToFront("Dove");
		
	
		System.out.println(blist.retrieveFirstElement());
		
	}
	@Test
	public void test4() {
		
		BasicLinkedList<String> blist = new BasicLinkedList<String>();		
		blist.addToFront("Zebra").addToFront("Bear").addToFront("Dove");
		
		
		System.out.println(blist.retrieveLastElement());
		System.out.println();
	}

	@Test
	public void test5() {
		
		BasicLinkedList<String> blist = new BasicLinkedList<String>();		
		blist.addToFront("Zebra").addToFront("Bear").addToFront("Dove");
		
		
		
		System.out.println(blist.getFirst());
	}
	@Test

	public void test6() {
		
		BasicLinkedList<String> blist = new BasicLinkedList<String>();
		
		blist.addToEnd("Zebra").addToEnd("Bear").addToEnd("Dove");
			
		System.out.println(blist.getLast());
		System.out.println();
		System.out.println();
	}
	@Test

	public void test7() {
		BasicLinkedList<String> b= new BasicLinkedList<String>();
		b.addToEnd("Zebra").addToEnd("Bear").addToEnd("Dove");
		System.out.print(b.removeAllInstances("Bear"));
	}
		

}
