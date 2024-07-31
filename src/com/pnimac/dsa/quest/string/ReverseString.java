package com.pnimac.dsa.quest.string;

/*
 * Write a function that reverses a string. The input string is given as an array of characters s.
 */
public class ReverseString {

	public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        
        while (start < end) {
            // Swap the characters at the start and end pointers
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            
            // Move the pointers towards the center
            start++;
            end--;
        }
    }
    
    public static void main(String[] args) {
        ReverseString solution = new ReverseString();
        
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        solution.reverseString(s);
        System.out.println(s);  // Output: ['o', 'l', 'l', 'e', 'h']
        
        s = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
        solution.reverseString(s);
        System.out.println(s);  // Output: ['h', 'a', 'n', 'n', 'a', 'H']
    }
}

