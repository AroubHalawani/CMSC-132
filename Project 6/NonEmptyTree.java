package searchTree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	
	private Tree<K,V> left, right;
	private K key;
	private V value;

	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) { 
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {

		V value = null;
		
		if(this.key.compareTo(key) == 0){ 
			// If the key parameter is the same as the instance key
			return value = this.value;
			
		}else if(key.compareTo(this.key) < 0){
			// If the key parameter is less than the instance key
			value = left.search(key);
		
		}else{
			// If the key parameter is greater than the instance key
			value = right.search(key);
			// return the value
		}
		// If the key is not in the tree, return null
		return value;
	
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) {

		if(this.key.compareTo(key) < 0) {
			// If the key parameter is less than the instance key
			right = right.insert(key, value);
						
		} else if (this.key.compareTo(key) > 0) {
			
			// If the key parameter is greater than the instance key
			left = left.insert(key, value);			
			
		} else {
			// If the key parameter is the same as the instance key
			this.value = value;
			
		}		
		return this;
		
	}
	
	public Tree<K, V> delete(K key) {
		 
		if(key.compareTo(this.key) > 0) {
			// If the instance key is grater than the key parameter
			right=right.delete(key);
			return this;
			
		} else if(key.compareTo(this.key) < 0) {
			// If the instance key is less than the key parameter	
			left=left.delete(key);
			return this;
			
		} else {
			
			// If the instance key is the same as the key parameter
			try {
				 
				// Delete the key by replacing with the left max key
				 this.key = left.max();
				 value = left.search(this.key);
				 left=left.delete(key);
				 return this;
				 
				 // If the tree is empty
			 } catch(TreeIsEmptyException u) {
				 
				 return right;				 
			 } 			
		}
	}
	
	public K max() throws TreeIsEmptyException {
		
		try {
			//let the variable be the right tree
			return right.max();
			// If the tree is empty
		} catch (TreeIsEmptyException r) {
			
			return key;			
		} 
	}

	public K min() throws TreeIsEmptyException {
		
		try {
			//let the variable be the left tree
			return left.min();
			// If the tree is empty
		} catch (TreeIsEmptyException r) {
			
			return key;
			
		}
	}
	public int size() {
		// Returning how many keys in each branch
		int size = left.size() + right.size()+ 1;
		
		return size;
	}

	public void addKeysToCollection(Collection<K> c) {
		// Add the key to the from the tree to the collection
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);		
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey) {

		// If the key is grater than toKey
		if (key.compareTo(toKey) > 0) {
			// return the key to left
			return left.subTree(fromKey, toKey); 
		// If the key is less than fromKey	
		} else if (key.compareTo(fromKey) < 0) {
			//return the key to the right
			return right.subTree(fromKey, toKey);
	
		} else {
			// If it is between them, return a subtree
			NonEmptyTree<K,V> tree = new NonEmptyTree<K,V>(key, value, left, right);
			tree.left = left.subTree(fromKey, toKey);
			tree.right = right.subTree(fromKey, toKey);
			return tree;
			
		}
	}
}