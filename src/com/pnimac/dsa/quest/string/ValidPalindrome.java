package com.pnimac.dsa.quest.string;

public class ValidPalindrome {

	public static boolean isPalindrome(String s) {
        // Remove non-alphanumeric characters and convert to lowercase
        StringBuilder filtered = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }
        
        // Check if the filtered string is a palindrome
        String filteredString = filtered.toString();
        int left = 0, right = filteredString.length() - 1;
        while (left < right) {
            if (filteredString.charAt(left) != filteredString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    public static void main(String[] args) {
        String testString1 = "A man, a plan, a canal: Panama";
        String testString2 = "race a car";
        
        System.out.println("Is the first string a palindrome? " + isPalindrome(testString1)); // Output: true
        System.out.println("Is the second string a palindrome? " + isPalindrome(testString2)); // Output: false
    }
}
