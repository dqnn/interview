package hatecode._2000_2999;

import java.util.*;

public class _2861MaximumNumberOfAlloys {
    /*
    2861. Maximum Number of Alloys

    You are the owner of a company that creates alloys using various types of metals. There are n different types of metals available, and you have access to k machines that can be used to create alloys. Each machine requires a specific amount of each metal type to create an alloy.

For the ith machine to create an alloy, it needs composition[i][j] units of metal of type j. Initially, you have stock[i] units of metal type i, and purchasing one unit of metal type i costs cost[i] coins.

Given integers n, k, budget, a 1-indexed 2D array composition, and 1-indexed arrays stock and cost, your goal is to maximize the number of alloys the company can create while staying within the budget of budget coins.

All alloys must be created with the same machine.

Return the maximum number of alloys that the company can create.

 

Example 1:

Input: n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]
Output: 2
    */


    /*
     * thinking process: O(knlg(budget + max(stock)))/O(1)
     * 
     * the problem is to say: given n metals, k machines, each machine can produce a product, while it need composition[i] to produce, its existing 
     * stock is stock[i] while cost is cost[i].
     * 
     * so first calculate how to calculate the cost if we want to produce m products.
     * see calc()
     * 
     * then we can use binary search to find the max product we can get 
     * 
     * one tricky here is if needed == budget, then because we want as more as possible products, so l move to left
     * 
     */
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int res = 0;
        for(int i = 0; i<composition.size(); i++) {
            int temp = helper(n, budget, composition.get(i), stock, cost);
            res = Math.max(res, temp);
        }
        
        return res;
    }
    
    
    private int helper(int n, int budget, List<Integer> host, List<Integer> stock, List<Integer> cost) {
        
        long l = 0, r = (int)1e10;
        
        while(l + 1 < r) {
            long m = l + (r-l)/2;
            long needed = calc(host, stock, cost, m);
            
            if(needed > budget) {
                r = m;
            } else {
                l = m;
            }
        }
        
        if (calc(host, stock, cost, r) <= budget) return (int)r;
        else return (int)l;
    }
    
    
    private long calc(List<Integer> host, List<Integer> stock, List<Integer> cost, long m) {
        long res = 0;
            for(int i = 0; i< host.size(); i++) {
                long needed = m * host.get(i)  - stock.get(i);
                res += cost.get(i) * (needed >= 0 ? needed : 0);
            }
        return res;
    }
}