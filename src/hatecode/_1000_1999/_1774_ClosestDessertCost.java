package hatecode._1000_1999;
public class _1774_ClosestDessertCost {
/*
1774. Closest Dessert Cost
You would like to make dessert and are preparing to buy the ingredients. You have n ice cream base flavors and m types of toppings to choose from. You must follow these rules when making your dessert:

There must be exactly one ice cream base.
You can add one or more types of topping or have no toppings at all.
There are at most two of each type of topping.
You are given three inputs:

baseCosts, an integer array of length n, where each baseCosts[i] represents the price of the ith ice cream base flavor.
toppingCosts, an integer array of length m, where each toppingCosts[i] is the price of one of the ith topping.
target, an integer representing your target price for dessert.
You want to make a dessert with a total cost as close to target as possible.

Return the closest possible cost of the dessert to target. If there are multiple, return the lower one.

 

Example 1:

Input: baseCosts = [1,7], toppingCosts = [3,4], target = 10
Output: 10
*/
    
    
    //thinking process: 
    //
    
    int res;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        res = baseCosts[0];
        for(int base: baseCosts)
            helper(base, toppingCosts, 0, target);
        return res;
    }
    private void helper(int current, int[] toppingCosts, int index, int target) {
        if( Math.abs(target - current) < Math.abs(target - res) || Math.abs(target - current) == Math.abs(target - res) && current < res)
            res = current;
        if(index==toppingCosts.length || current >= target) return;
        helper(current, toppingCosts, index + 1, target);
        helper(current + toppingCosts[index], toppingCosts, index + 1, target);
        helper(current + toppingCosts[index]*2, toppingCosts, index + 1, target);
    }
}