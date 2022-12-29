/*
* This class counts the word frequency
* Student Name : Aroub Halawani
 */
package frequency;

import java.util.Iterator;

import org.w3c.dom.Node;

public class Frequency<E extends Comparable> implements Iterable<E>{
    private Node first;	//starting node
    private Node parent;	//parent of currently processed node
    private int N;	//number of words
    
    /**
     * Linked List Node
     */
    private class Node {
    	private E key;
    	private int count;
        private Node next;
        /**
         * 	Constructor
         */
        Node(E e){
           key = e;
           count = 1;
           next = null;
        }
        /**
         * 	Constructor
         */
        Node(E e, Node r){
            key = e;
            count = 1;
            next = r;
         }
        
        @Override 
        public String toString(){
        	return "("+key +","+count+")";
        }
        
        public int compareTo(Node c) {  
        	if (count == c.count) {        		
        		return 0;        		
        	} else if (count > c.count) {
        		return -1;
        	} else {       	
        		return 1;
        	}
        }
    }
   /**
    * Inserts a word into linked list
    * @param key to be inserted 
    * @return true if the key is inserted successfully.
    */
    public boolean insert(E key){
    	// If the list is empty
    	if (first == null) {
    		// Adding the word to the front
            first = new Node(key, null); 
            // Increase the number of words
            N++;
        } else {
        	
            Node curr = first;
            Node prev = null;
            
            while (curr != null) { 
            	// If the key is found
            	if (curr.key.equals(key)) {
            		
            		curr.count++; 		
            		return true;
            	}
            	prev = curr;
                curr = curr.next; 
            }
            // The last key
            prev.next = new Node(key,null);  
        }
        return true;
	}

       
   /**
    * removes the nodes with given key
    * @param key: 
    * @return the deleted node
    */
    private Node remove(E key){   	
    	Node current=first;
    	// If the first key equals the given key
    	if(first.key.equals(key)) {
    		first=first.next;
    		return current;
    	}
		Node temp=null;
		// Going through the list
		while(current!=null && !current.key.equals(key)) {
			temp=current;
			current=current.next;
		}
		if(current != null) {
			temp.next=temp.next.next;
			current.next=null;
			return current;
		}
		
		return temp;
	}

    /**
     * Inserts a node into correct location in the linked list
     * @param r is the node to be inserted
     * @return true if successful
     */
    private boolean insert(Node r){
    	if(first==null) {
			first=r;
			return true;
		} 
    	
		Node current = first;
		// Going through the list
		while(current.next!=null && ! current.next.key.equals(r.key)) {
			current=current.next;
		}
		if(current.next!=null) {
			current.next.count++;
			return false;
		}
		current=first;
		// Going through the list
		while(current.next!=null && current.next.count<=r.count) {
			current=current.next;
		}
		r.next=current.next;
		current.next=r;
		return true;
	}
    
    /**
     * @param k is the key to be searched for
     * @return the node that has the word as its key
     */
    private Node find(E k){
    	// Going over the list to find E
    	for(Node curr = first; curr != null; curr = curr.next) {
    		// If we found E
			if (curr.key.equals(k)) {
				//return the node of the key
				return curr;				
			}
		}
    	// If we did not find E in the list, return null;
		return null;	
	}
    
    /**
     * 
     * @param key is the key to be searched for
     * @return frequency of the key. Returns -1 if key does not exist
     * 
     */
    public int getCount(E key){
    	// If the key does not exist
    	if (key == null) {
            return -1;
        }    	
        Node curr = first;
        // Going over the list
        while (curr != null) {
        	// If the key is found
        	if (curr.key.equals(key)) {
        		//return the count of the key
        		return curr.count;
        	}
            curr = curr.next;
        }
        // If the key is not found
        return 0;
	}
    
    private void List() {
    	boolean switched = true;
    	while(switched) {
        	switched = false;
        	Node cur = first;
        	Node prev = null;
        	while(cur.next != null) {
        		prev = cur;
        		cur = cur.next;
        		if (prev.compareTo(cur)==0) {
        			if (prev.key.compareTo(cur.key) > 0) {
        				switched = true;
        				E temp = cur.key;
        				cur.key = prev.key;
        				prev.key = temp;
        				
        				int tempInt = cur.count;
        				cur.count = prev.count;
        				prev.count = tempInt;
        			}
        		}
        		else if (prev.compareTo(cur)>0) {
        			switched = true;
    				E temp = cur.key;
    				cur.key = prev.key;
    				prev.key = temp;
    				
    				int tempInt = cur.count;
    				cur.count = prev.count;
    				prev.count = tempInt;
        		}
        	}
    	}
    }
    /**
     * Returns the first n words and count
     * @param n number of words to be returned
     * @return first n words in (word, count) format
     */
    public String getWords(int n){
    	// Calling the method that sort the list
    	List();
    	Node current =first;
    	String result = "";
    	// Going through the list
		for(int count=0; count<n;count++) {
			// If the Node is empty
			if (current == null) {
				break; // breaking the loop
			}
			// If the Node is not empty, add it the String
			result += current.toString() + ",";
			current=current.next;
		}
		return result;
	}
    
    /**
     * Frequency List iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new FreqIterator(first);
    }
    
    /**
     * 
     * Frequency List iterator class
     *
     */
    private class FreqIterator implements Iterator<E>{
        
        private Node iterator;

    	public FreqIterator(Node initial) {
    		List();
    		iterator = initial;
    	}
        @Override
        public boolean hasNext() {
    	
        	if(iterator==null) {    		
        		return false;  	  
        	}
        	return true;
        }
        @Override
        public E next() {
        	if(hasNext()==false) {
        		return null;
        	}
        	E element = iterator.key;
        	iterator=iterator.next;
        	return element;
  		
      }
    }
}
    
