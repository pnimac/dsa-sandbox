package com.pnimac.dsa.string;

import java.util.HashMap;

/*
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 */
public class FirstNonRepeatingCharacter {

	public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        for(char c: s.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1); 
        }
        
        for(int i=0; i<s.length(); i++){
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }
}
