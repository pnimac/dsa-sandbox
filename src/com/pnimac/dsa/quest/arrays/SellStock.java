package com.pnimac.dsa.quest.arrays;

/**
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the ith day.
 * 
 * On each day, you may decide to buy and/or sell the stock. You can only hold
 * at most one share of the stock at any time. However, you can buy it then
 * immediately sell it on the same day.
 * 
 * Find and return the maximum profit you can achieve.
 */
public class SellStock {

	public int maxProfit(int[] prices) {
        int totalProfit = 0;
        
        // Iterate through the prices array from the second element to the last one
        for (int i = 1; i < prices.length; i++) {
            // If the price of the current day is higher than the previous day, add the difference to the total profit
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        
        // Return the total profit
        return totalProfit;
    }
    
    public static void main(String[] args) {
    	SellStock solution = new SellStock();
        
        int[] prices = {7,1,5,3,6,4};
        System.out.println("Maximum profit: " + solution.maxProfit(prices));  // Output: 7
        
        prices = new int[]{1,2,3,4,5};
        System.out.println("Maximum profit: " + solution.maxProfit(prices));  // Output: 4
        
        prices = new int[]{7,6,4,3,1};
        System.out.println("Maximum profit: " + solution.maxProfit(prices));  // Output: 0
    }
}
