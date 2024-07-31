package com.pnimac.dsa.quest.string;

import java.util.HashMap;
import java.util.Map;

/*
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 */
public class ValidAnagram {
	
	public static boolean isAnagram(String s, String t) {
        // If the lengths of the strings are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        
        // Create a frequency map for characters in the first string
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        
        // Decrement the frequency based on characters in the second string
        for (char c : t.toCharArray()) {
            if (!charCountMap.containsKey(c)) {
                return false;
            }
            charCountMap.put(c, charCountMap.get(c) - 1);
            if (charCountMap.get(c) < 0) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        boolean result = isAnagram(s, t);
        System.out.println("Is t an anagram of s? " + result);  // Output: true

        s = "rat";
        t = "car";
        result = isAnagram(s, t);
        System.out.println("Is t an anagram of s? " + result);  // Output: false
    }
}