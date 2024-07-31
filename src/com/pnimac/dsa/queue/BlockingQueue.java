package com.pnimac.dsa.queue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

	private Queue<T> queue;
	private int capacity;
	
	public BlockingQueue(int capacity){
		this.capacity = capacity;
		queue = new LinkedList<T>();
	}
	
	public synchronized void put(T item) {
		while(queue.size() == capacity) {
			System.out.println("Queue is full, waiting..");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.add(item);
		notifyAll();
	}

	
	public synchronized T take() {
		while(queue.isEmpty()) {
			System.out.println("Queue is empty, waiting..");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T temp = queue.poll();
		notifyAll();
		return temp;
	}
}
