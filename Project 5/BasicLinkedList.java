package listClass;

import java.util.Iterator;

public class BasicLinkedList<T> implements Iterable<T> {

	// A private static nested class
	private static class Node<T> {
		
	    //private instance variables of the nested class 
		
		private T data;
	    private Node<T> next;

	    // A constructor that take one parameter
	    
	    private Node(T data) {
	        this.data = data;
	        next = null;
	    }
	}
	
	// private instance variables 
	
	private Node<T> head;
	private Node<T> tail;
	private int currentSize;
	

	// A constructor that initializes the list as empty
	
	public BasicLinkedList() {
	    
		head = null;	 
	    tail = null;	   
	}
	
	// Getter for the size variable
	
	public int getSize() {
		
	    return currentSize;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {		
	   
		Node<T> end = new Node<T>(data);
	    
		//Adds the element to the tail of the list.
	    if (tail == null) {
	    	head = end;
	        tail = end;
	        
	    } else {
	    	
	    	Node<T> temp = head;
	    	
	        if (temp.next != null) {
	            temp = temp.next;
	        }
	        
	        if(temp.next == null) {
	         
	        	temp.next = end;
	 	        tail = end;
	        }
	       

	    }
	    currentSize++;
	    
	    return this;
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		
	    Node<T> front = new Node<T>(data);
	    
	    //Adds the element to the head of the list.
	    if(head==null) {
	    	head=front;
	    	tail=front;
	    	
	    } else {
	    	
	    	front.next = head;
		    head = front;
	    }
	    
	    currentSize++;
	    
	    
	    return this;
	}
	
	public T getFirst() {
		
	    if (head == null) {
	    	// Returns null if the list is empty 
	    	return null;
	    	
	    	
	    } else {
	    	// Returns the head element (without removing it) 
	    	return head.data;
	    }
	    
	}
	
	public T getLast() {
		
	    if(tail == null){
	    	// Returns null if the list is empty 
	    	return null;
	    	    	
	    } else {
	    	//Returns the tail element (without removing it)
	    	return tail.data;
	    }
	    
	}
	
	public T retrieveFirstElement() {
		
		Node<T> firstElement = head;
		
	    // If the list is empty
	   if(currentSize==0) {
		   
		   return null;
		   
	   } else if(currentSize==1) {
		   // If the list has one element
		   head=null;
		   tail=null;
		   currentSize--;
		   return firstElement.data;
		   
	   } else {
		   // If the list has more than one element
		   head=head.next;
		   currentSize--;
		   return firstElement.data;
	   }

	}
	public T retrieveLastElement() {

		Node<T> lastElement  = head;
		
		// If the list is empty
		
		if (currentSize == 0) {
			
			return null;
			
		} else if (currentSize == 1) {
			
			// If the list has one element
			head = null;
	        tail = null;
	        currentSize--;
	        return lastElement .data;
	        
		} else {
			
			// If the list has more than one element
			Node<T> save = head;
            
        	while (lastElement.next != null) {
            	
            	save = lastElement;
                lastElement = lastElement.next;
            }

            tail = save;
            tail.next = null;
		}
									
		currentSize--;
	    return lastElement.data;

	    
	}

	public BasicLinkedList<T> removeAllInstances(T targetData){
		
		//Removes all instances of the target element from the list.

		Node<T> temp=head;
		
		if(temp.data==targetData) {
			
			if(temp==head) {
				
				head=head.next;
				temp=head;
				
			} else if( temp==tail) {
				
				temp=null;
				tail=null;
				tail.next=null;
				
			} else {
				
				temp=temp.next;
			}
		}
		
		currentSize--;
		return this;
		
	}
	
	public Iterator<T> iterator() {
		
		//Returns an instance of an anonymous inner class
		
	    return new Iterator<T>() {

	        Node<T> current = head;
	        
	        public boolean hasNext() {
	        	
	            return (current != null);
	        }

	        public T next() {
	        
	        	T data = current.data;
                current = current.next;
                return data;
	        		              
	        }	        
	    };
	}	
}
