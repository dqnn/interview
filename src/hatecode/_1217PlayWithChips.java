package hatecode;
public class _1217PlayWithChips {
/*
1217. Play with Chips
There are some chips, and the i-th chip is at position chips[i].

You can perform any of the two following types of moves any number of times (possibly zero) on any chip:

Move the i-th chip by 2 units to the left or to the right with a cost of 0.
Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
There can be two or more chips at the same position initially.

Return the minimum cost needed to move all the chips to the same position (any position).

 

Example 1:

Input: chips = [1,2,3]
Output: 1
*/
    //thinking process:
    //given an array A, each value in A reprents a position, we can move
    //any chips to +-2 position without any cost but +-1 with cost 1,
    //return the mini cost to move all to same position
    
    //so from the problem we can see moving between odd or even has no cost
    //we can see that if there are only two chips, one is odd ,another is even, then
    //it will be 1, suppose they can move to closest each other without cost
    //so above is the simplest model, we can move any chips to the two locations,
    //
    public int minCostToMoveChips(int[] A) {
        if(A == null ||A.length < 1) return 0;
        
        int odd = 0, even = 0;
        for(int a : A) {
            if(a % 2 == 0) even++;
            else odd++;
        }
        
        return Math.min(odd, even);
    }
}