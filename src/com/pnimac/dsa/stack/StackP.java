package com.pnimac.dsa.stack;

public class StackP {

		private int topPtr;
		private int[] elements;
		private int maxSize;
		
		public StackP() {
			
		}
		
		public StackP(int size) {
			this.maxSize = size;
			topPtr = -1;
			elements = new int[maxSize];
		}
		
		private boolean isFull() {
			return (topPtr == maxSize-1);
		}
		
		private boolean isEmpty() {
			return topPtr == -1;
		}
		
		private int count() {
			return topPtr + 1;
			
		}
		
		public void push(int newElement) {
			
			if(isFull()) {
				System.out.println("Stack Overflow.");
			}else {
				elements[++topPtr] = newElement;
			}
			
		}
		
		public int pop() {
			if(isEmpty()) {
				System.out.println("Stack is empty.");
				return -1;
			}else {
				return elements[topPtr--];
			}
		}
		
		public int peek() {
			if(isEmpty()) {
				System.out.println("Stack is empty.");
				return -1;
			}else {
				return elements[topPtr];
			}
		}
		
		public static void main(String args) {
			StackP myStack = new StackP(5); // Creating a stack of size 5

	        myStack.push(1);
	        myStack.push(2);
	        myStack.push(3);

	        System.out.println("Top element is: " + myStack.peek()); // Output: 3
	        System.out.println("Stack size is: " + myStack.count()); // Output: 3

	        System.out.println("Popped element is: " + myStack.pop()); // Output: 3
	        System.out.println("Top element is: " + myStack.peek()); // Output: 2

	        System.out.println("Stack is empty: " + myStack.isEmpty()); // Output: false

	        myStack.push(4);
	        myStack.push(5);
	        myStack.push(6); // Stack is full now

	        myStack.push(7); // Should print "Stack is full. Cannot push 7"
		}
		
	
}
