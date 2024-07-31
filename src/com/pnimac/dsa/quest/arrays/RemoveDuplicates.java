package com.pnimac.dsa.quest.arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, 
 * remove the duplicates in-place such that each unique element appears only once. 
 * The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        // Initialize the second pointer
        int j = 1;
        
        // Iterate through the array with the first pointer
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the previous one, it's unique
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        
        // The length of the unique part of the array is indicated by j
        return j;
    }
    
    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = solution.removeDuplicates(nums);
        
        System.out.println("Number of unique elements: " + k);  // Output: 5
        System.out.print("Unique elements: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");  // Output: 0 1 2 3 4
        }
    }
}