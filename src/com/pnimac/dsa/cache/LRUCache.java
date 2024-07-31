package com.pnimac.dsa.cache;

import java.util.HashMap;

public class LRUCache<K, V> {

	private int capacity;
	private HashMap<K, Node<K, V>> cacheMap;
	private DoublyLinkedList<K, V> orderList;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cacheMap = new HashMap<>();
		this.orderList = new DoublyLinkedList<K, V>();
	}

	public synchronized V get(K key) {
		Node<K, V> node = cacheMap.get(key);

		if (node == null) {
			return null; // Cache is empty
		}

		// Move the accessed node to the front of the list
		orderList.remove(node);
		orderList.addToTheTop(node);

		return node.val;
	}

	public synchronized void put(K key, V val) {

		Node<K, V> node = cacheMap.get(key);
		if (node != null) {
			node.val = val;
			cacheMap.put(key, node);
			orderList.remove(node);
			orderList.addToTheTop(node);
		} else {
			if (cacheMap.size() >= capacity) {
				// Remove the least recently used item
				Node<K, V> evictedNode = orderList.removeLast();
				cacheMap.remove(evictedNode.key); // Use the key of the evicted item to update the cache

			}
		}
		// Move the new node to the front of the list
		node = new Node<>(key, val);
		orderList.addToTheTop(node);
		cacheMap.put(key, node);

	}

	public static void main(String[] args) {
		LRUCache<Integer, String> lruCache = new LRUCache<>(3);

		lruCache.put(1, "Product One");
		lruCache.put(2, "Product Two");
		lruCache.put(3, "Product Three");

		System.out.println(lruCache.get(1)); // Output: One
		System.out.println(lruCache.get(2)); // Output: Two

		lruCache.put(4, "Product Four"); // This will evict key 3 (least recently used)

		System.out.println(lruCache.get(3)); // Output: null (evicted)
		System.out.println(lruCache.get(4)); // Output: Four
	}

	private static class Product {
		private String id;
		private String details;

		public Product(String id, String details) {
			this.id = id;
			this.details = details;
		}
	}

	private static class Node<K, V> {
		private K key;
		private V val;
		private Node<K, V> previous;
		private Node<K, V> next;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
			this.previous = previous;
			this.next = next;
		}
	}

	private static class DoublyLinkedList<K, V> {
		private Node<K, V> head;
		private Node<K, V> tail;

		public DoublyLinkedList() {
			head = new Node<K, V>(null, null);// Initializing Sentinel head node
			tail = new Node<K, V>(null, null);// Initializing Sentinel tail node
			head.next = tail;
			tail.previous = head;
		}

		/*
		 * The most recently used items is placed immediately after the head. The
		 * purpose of adding node right after the head (rather than directly on top of
		 * the head node) is to maintain a specific structure for tracking the most
		 * recently used items.
		 */
		public void addToTheTop(Node<K, V> newNode) {
			newNode.next = head.next;
			newNode.previous = head;
			head.next.previous = newNode;
			head.next = newNode;
		}

		public void remove(Node<K, V> node) {
			node.previous.next = node.next;
			node.next.previous = node.previous;
			node.next = node.previous = null; // Clear the removed node's references
		}

		public Node<K, V> removeLast() {
			if (head.next == tail) {
				return null; // List is empty.
			}

			Node<K, V> lastEntry = tail.previous;
			remove(lastEntry);
			return lastEntry;// return the evicted entry
		}
	}

	private static class DoublyLinkedListWithoutSentinelNode<K, V> {
		private Node<K, V> head; // Start of the list
		private Node<K, V> tail; // End of the list

		DoublyLinkedListWithoutSentinelNode() {
			head = null;
			tail = null;
		}

		void addToTheTop(Node<K, V> node) {
			if (head == null) {
				head = tail = node; // List is empty, so head and tail point to the new node
			} else {
				node.next = head;
				head.previous = node;
				head = node;
			}
		}

		void remove(Node<K, V> node) {
			if (node == head) {
				head = node.next;
				if (head != null) {
					head.previous = null;
				}
			} else if (node == tail) {
				tail = node.previous;
				if (tail != null) {
					tail.next = null;
				}
			} else {
				node.previous.next = node.next;
				node.next.previous = node.previous;
			}
			// Clear the removed node's references
			node.next = node.previous = null;
		}

		Node<K, V> removeLast() {
			if (tail == null) {
				return null; // List is empty
			}
			Node<K, V> last = tail;
			remove(last);
			return last;
		}
	}
}
