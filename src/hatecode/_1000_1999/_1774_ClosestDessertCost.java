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
    
    //thinking process: O(3^n)/O(n)
    
    //the problem is to say: given baseCosts array and toppingCosts array, you will need 
    //to find the min diff between:
    //a. base[i] + n * topping[j], n = 0,1,2,3..., i=0..n-1, j=0 ...n-1
    //b. target
    
    //find the min diff, previously there we can use binary search to find the closest one, but 
    //now we do not have an aray to perform search, so we need to figure out all possible ways, 
    
    //we start from currentCost = 0, then each time, we have 3 days to digging down, 
    //then everytime we tried a new combination, then we should updates the result.
    
    //here if we tried all toppings or base costs, then exit
    
    //TODO: how to design the visited nodes
    
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
    
    //DP 
    public int closestCost_(int[] base, int[] top, int tar) {
        int m=top.length, size=100000;
        for(int i:base) size=Math.min(size,i);
        
        int min=100000;
        for(int i:top) min=Math.min(min,i);
        size+=min;
        
        //minimum size for the dp
        size=Math.max(size+1,2*tar+1);
        boolean[][] dp=new boolean[m+1][size];
        
        //base case
        for(int b:base){
            if(b<size) dp[0][b]=true;
        }
        
        //Generating the rest of DP table
        for(int i=1;i<=m;i++){
            for(int j=1;j<size;j++){
                if(dp[i-1][j]){
                    dp[i][j]=true;
                    
                    //using one topping
                    if(j+top[i-1]<size){
                        dp[i][j+top[i-1]]=true;
                        
                        //using same topping again if possible
                        if(j+2*top[i-1]<size)
                            dp[i][j+2*top[i-1]]=true;
                    }
                }
            }
        }
        if(dp[m][tar])
            return tar;
        int res=-1000000;
        
        //computing nearest possible answer to target
        for(int i=tar-1;i>=0;i--){
            if(dp[m][i]){
                res=i;
                break;
            }
        }
        for(int i=tar+1;i<size;i++){
            if(dp[m][i]){
                res=(tar-res)<=(i-tar)?res:i;
                break;
            }
        }
        return res;
    }
}