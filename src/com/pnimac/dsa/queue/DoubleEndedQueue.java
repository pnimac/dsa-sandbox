package com.pnimac.dsa.queue;

public class DoubleEndedQueue {

	private int[] elements;

	private int maxSize;

	private int frontPtr;
	private int endPtr;

	public DoubleEndedQueue() {

	}

	public DoubleEndedQueue(int maxSize) {
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
	
	public void enqueueFirst(int newElement) {
		if (isFull()) {
			System.out.println("Queue is full.");
		} else {
			frontPtr = (frontPtr - 1) % maxSize;
			elements[frontPtr] = newElement;
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
	
	public int dequeueLast() {
		if (isEmpty()) {
			System.out.println("Queue is empty.");
			return -1;
		} else {
			int temp = elements[endPtr];
			endPtr = (endPtr - 1) % maxSize;
			return temp;
		}
	}

}
