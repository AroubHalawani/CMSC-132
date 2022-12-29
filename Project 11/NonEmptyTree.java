package tree;

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

	/* Provide whatever instance variables you need */
	private Tree<K,V> left, right;
	private K key;
	private V value;

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) { 
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public V search(K key) {
		if(this.key.compareTo(key) == 0){ 
			return this.value;
		}else if(key.compareTo(this.key) < 0){
			return this.left.search(key);
		}else {
			return this.right.search(key);
		}
	}	
	public NonEmptyTree<K, V> insert(K key, V value) {
		if(this.key.compareTo(key) < 0) {
			this.right = right.insert(key, value);						
		} else if (this.key.compareTo(key) > 0) {
			this.left = left.insert(key, value);			
		} else {
			this.value = value;
		}		
		return this;
	}	
	public Tree<K, V> delete(K key) {
		if(key.compareTo(this.key) > 0) {
			right=right.delete(key);
			return this;
			
		} else if(key.compareTo(this.key) < 0) {	
			left=left.delete(key);
			return this;		
		} else {
			try {
                 this.key = left.max();
                 value = left.search(left.max());
                 left = left.delete(left.max());
			 } catch(TreeIsEmptyException u) {				 
				 try {
                     this.key = right.min();
                     value = right.search(right.min());
                     right = right.delete(right.min());
				 } catch (TreeIsEmptyException e) {
					 return EmptyTree.getInstance();
				 }
				 				 
			 }
			return this;
		}
	}
	public K max() {
		try {
			return right.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}
	public K min() {
		try {
			return left.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}
	public int size() {
		int size= this.left.size()+this.right.size()+1;
		return size;
	}
	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);
	}	
	public Tree<K,V> subTree(K fromKey, K toKey) {
		
		if(toKey.compareTo(this.key)<0) {
			
			return this.left.subTree(fromKey, toKey);
			
		} else if(this.key.compareTo(fromKey)<0) {
			
			return this.right.subTree(fromKey, toKey);
			
		}else {
			NonEmptyTree<K,V> tree = new NonEmptyTree<K,V>(this.key,this.value,left,right);
			tree.left=left.subTree(fromKey, toKey);
			tree.right=right.subTree(fromKey, toKey);
			return tree;
		}
	}	
	public int height() {
		int height;
		height= Math.max(left.height(), right.height())+1;
		return height;
	}	
	public void inorderTraversal(TraversalTask<K,V> p) {
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}
}