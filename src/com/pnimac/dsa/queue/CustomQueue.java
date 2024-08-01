package com.pnimac.dsa.queue;

public class CustomQueue {

	private int[] elements;

	private int maxSize;

	private int frontPtr;
	private int endPtr;

	public CustomQueue() {

	}

	public CustomQueue(int maxSize) {
		this.maxSize = maxSize;
		this.elements = new int[maxSize];
		this.frontPtr = -1;
		this.endPtr = -1;
	}

	public boolean isFull() {
		return endPtr == maxSize - 1;
	}

	public boolean isEmpty() {
		return frontPtr == -1;
	}

	public void enqueue(int newElement) {
		if (isFull()) {
			System.out.println("Queue is full.");
		} else {
			endPtr = (endPtr + 1) % maxSize;
			elements[endPtr] = newElement;
		}
	}

	public int dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty.");
			return -1;
		} else {
			int temp = elements[frontPtr];
			frontPtr = (frontPtr + 1) % maxSize;
			return temp;
		}
	}

	public static void main(String[] args) {

		CustomQueue queue = new CustomQueue(5);

		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);

		System.out.println("Dequeued item is: " + queue.dequeue());

		queue.enqueue(60); // This should print an overflow message

		System.out.println("Dequeued item is: " + queue.dequeue());

	}

}
