package com.pnimac.dsa.list;

public class CircularDoublyLinkedList<T> {

	Node<T> head;

	public void add(T data) {
		Node<T> newNode = new Node<T>(data);

		if (head == null) {// adding first element to the chain
			head = newNode;
			newNode.next = head;
			newNode.previous = head;
		} else {// adding element at the end of existing chain
			head.previous.next = newNode;
			newNode.previous = head.previous;
			newNode.next = head;
			head.previous = newNode;
		}
	}

	public void remove(T data) {

		if (head == null) {
			return;
		}

		Node<T> current = head;
		do {
			if (current.data == data) {
				if (current.previous == head && current.next == head) { // found the data in a single element chain
					head = null;
				} else {// found the data anywhere in the multiple element chain
					current.previous.next = current.next;
					current.next.previous = current.previous;
					if (current == head) {
						head = current.next;
					}
				}
			}
			current = current.next;
		} while (current != head);

	}

	public void print() {

		if (head == null) {
			System.out.println("Empty List");
			return;
		}

		Node<T> current = head;
		do {
			System.out.println(current.data);
			current = current.next;
		} while (current != head);
	}

	public void printReverse() {
		if (head == null) {
			System.out.println("Empty List");
			return;
		}

		Node<T> current = head.previous;
		do {
			System.out.println(current.data);
			current = current.previous;
		} while (current != head.previous);

	}

	public static void main(String[] args) {
		CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<Integer>();

		// Adding elements
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// Printing the list
		System.out.print("Circular Doubly Linked List: ");
		list.print(); // Output: Circular Doubly Linked List: 1 2 3 4

		// Removing an element
		list.remove(3);
		System.out.print("After removing 3: ");
		list.print(); // Output: After removing 3: 1 2 4

		// Printing the list in reverse order
		System.out.print("Circular Doubly Linked List in Reverse: ");
		list.printReverse(); // Output: Circular Doubly Linked List in Reverse: 4 2 1
	}

	private static class Node<T> {
		T data;
		Node<T> next;
		Node<T> previous;

		private Node(T data) {
			this.data = data;
		}
	}

}
