package com.pnimac.dsa.map;

import java.util.*;

public class HashMapP<K, V> {

	private Node<K, V>[] bucket;
	private int INITIAL_DEFAULT_CAPACITY = 16;
	private float loadFactor = 75f;
	private int currentBucketSize; // tracks the number of key-value pairs currently stored in the HashMap. Unlike
									// bucket.length, which is fixed after initialization, size changes dynamically
									// as elements are added or removed.

	public HashMapP() {
		this.bucket = new Node[INITIAL_DEFAULT_CAPACITY];
	}

	private int hash(K key) {
		return Objects.hashCode(key) % bucket.length;
	}

	private void resize() {

		int newCapacity = bucket.length * 2; // double the capacity
		Node<K, V>[] newBucket = new Node[newCapacity];

		for (Node<K, V> oldNode : bucket) {// iterate through each Nodes at each index in the old bucket.
			while (oldNode != null) {// iterate through each of the nodes in the chain at the index in the old
										// bucket.
				int hashkey = Objects.hashCode(oldNode.key) % newCapacity;// get the new index for the new Bucket.
				if (newBucket[hashkey] == null) {// no Nodes exist at this index in the new bucket.
					newBucket[hashkey] = oldNode;// simply add the oldNode to the index
				} else {// if new Bucket has existing nodes at this index, we need to add the current
						// oldNode to the chain.
					Node<K, V> newBucketExistingNode = newBucket[hashkey];// get the existing node at the top of the
																			// chain at the index in the new bucket.
					Node<K, V> nextNode = oldNode.nextNode;// preserve the reference to the nextNode for next iteration
					oldNode.nextNode = newBucketExistingNode;// Update the current old Node's nextNode to point to the
																// existing head of the chain at the index in the new
																// bucket
					newBucket[hashkey] = oldNode;// move the current old Node as the head of the chain at the index in
													// the new bucket
					oldNode = nextNode;// set the oldNode for the next iteration till we reach end of the chain at this
										// index.
				}
			}
		}
		bucket = newBucket;
	}

	public void put(K key, V value) {

		if (currentBucketSize > bucket.length * loadFactor) {
			resize();
		}

		int hashkey = hash(key); // Get the hash of the key. This will be the index in the bucket for our new
									// Node.

		Node newNode = new Node(key, value, null);// create a new Node with key, value passed, and a null reference for
													// nextNode property

		Node<K, V> existingNode = bucket[hashkey];// Get the existing Node at the index of hashkey.

		Node<K, V> refPreviousNode = null;

		if (existingNode == null) { // check if bucket already has any Node at the index of hashkey.
			bucket[hashkey] = newNode; // if it doesn't exist, simply put the newNode at the index of hashkey.
			currentBucketSize++; // increase the bucket size.
		} else {
			while (existingNode != null) {// else, iterate through each of the existingNodes in the chain
				if (existingNode.key.equals(newNode.key)) {// if keys are equal, replace the value for existingNode and
															// exit the loop
					existingNode.value = value;
					return;
				} else { // if keys are not equal but hash is same, then iterate till the end of the
							// linked list (chain) of existing Nodes present at the index of the hashkey in
							// the bucket.
					refPreviousNode = existingNode;// maintain a reference to the existingNode, at the end of the loop,
													// this will represent the last node in the chain.
					existingNode = existingNode.nextNode;// update the existingNode to its nextNode reference for the
															// next iteration of the loop.
				}
			}
			refPreviousNode.nextNode = newNode;// set the nextNode reference in the last node of the chain to our new
												// Node.
			currentBucketSize++; // increase the bucket size.
		}
	}

	public V get(K key) {
		int hashkey = hash(key); // get the hash of the key. This will be the index in the bucket for the
									// returning node.

		Node<K, V> existingNode = bucket[hashkey];
		if (existingNode != null) {
			while (existingNode != null) { // we iterate through the nodes in the chain until we match the key
				if (existingNode.key.equals(key)) {
					return existingNode.value;
				}
				existingNode = existingNode.nextNode;
			}
		}
		return null;// if no node is found at the index, return null.
	}

	public void remove(K key) {
		int hashkey = hash(key); // get the hash of the key. This will be the index in the bucket for the
									// returning node.

		Node<K, V> existingNode = bucket[hashkey];
		Node<K, V> prevNode = null; // maintain a reference to previous node
		if (existingNode != null) {
			while (existingNode != null) { // we iterate through the nodes in the chain until we match the key
				if (existingNode.key.equals(key)) {
					if (prevNode == null) {// we find the matching key in the first node at the index in the bucket.
						bucket[hashkey] = existingNode.nextNode;// we remove existingNode from the chain by updating
																// making the reference to the nextNode as the first
																// node.
					} else {
						prevNode.nextNode = existingNode.nextNode;// we remove existingNode from the chain by updating
																	// previous Node reference to the nextNode.
					}
					currentBucketSize--;
					return;// exit the loop
				}
				existingNode = existingNode.nextNode;// update the existingNode to its nextNode reference for the next
														// iteration of the loop.
			}

		}
	}

	public boolean containsKey(K key) {
		int index = hash(key);
		Node<K, V> current = bucket[index];

		while (current != null) {
			if (current.key.equals(key)) {
				return true;
			}
			current = current.nextNode;
		}
		return false;
	}

	public static void main(String[] args) {
		// Create an instance of HashMapP
		HashMapP<String, Integer> map = new HashMapP<>();

		// Test the put method
		System.out.println("Adding key-value pairs:");
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		map.put("Four", 4);

		// Test the get method
		System.out.println("\nRetrieving values:");
		System.out.println("Value for 'One': " + map.get("One")); // Expected: 1
		System.out.println("Value for 'Two': " + map.get("Two")); // Expected: 2
		System.out.println("Value for 'Three': " + map.get("Three"));// Expected: 3
		System.out.println("Value for 'Four': " + map.get("Four")); // Expected: 4

		// Test the containsKey method
		System.out.println("\nChecking if keys exist:");
		System.out.println("Contains 'One': " + map.containsKey("One")); // Expected: true
		System.out.println("Contains 'Two': " + map.containsKey("Two")); // Expected: true
		System.out.println("Contains 'Five': " + map.containsKey("Five")); // Expected: false

		// Test the remove method
		System.out.println("\nRemoving a key-value pair:");
		map.remove("Three");
		System.out.println("Value for 'Three' after removal: " + map.get("Three")); // Expected: null
		System.out.println("Contains 'Three': " + map.containsKey("Three")); // Expected: false

		// Add more elements to test resize
		System.out.println("\nAdding more key-value pairs:");
		for (int i = 5; i <= 20; i++) {
			map.put("Key" + i, i);
		}

		// Verify addition and resize operation
		System.out.println("\nRetrieving values after adding more elements:");
		for (int i = 1; i <= 20; i++) {
			System.out.println("Value for 'Key" + i + "': " + map.get("Key" + i)); // Expected: i
		}
	}

	private class Node<K, V> {
		private K key;
		private V value;

		private Node<K, V> nextNode = null;

		public Node() {
		}

		public Node(K key, V value, Node<K, V> next) {
			this.key = key;
			this.value = value;
			this.nextNode = next;
		}
	}
}
