package hatecode;

import java.util.*;
public class BagOfTokens {
/*
948. Bag of Tokens
You have an initial power P, an initial score of 0 points, and a bag of tokens.

Each token can be used at most once, has a value token[i], and has potentially two ways to use it.

If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
Return the largest number of points we can have after playing any number of tokens.

 

Example 1:

Input: tokens = [100], P = 50
Output: 0

*/
    //Buy at the cheapest and sell at the most expensive.
    
    //thinking process: 
    //given array and initial points, you can use the token to gain 1 point but lose token[i] or lose 1 point 
    //by gaining token[i] power. 
    
    //so we sort the array first, so we can gian most power while lose any 1 point, and we gain 1point by
    //losing least power
    
    //since our purpose is to get max points, we prioritize the points, let points to be the first if
    public int bagOfTokensScore(int[] tokens, int P) {
        
        Arrays.sort(tokens);
        
        int res = 0, points = 0, i = 0, j = tokens.length - 1;
        
        while(i <= j) {
            if (P >= tokens[i]) {
                P -= tokens[i++];
                res = Math.max(res, ++points);
            } else if (points > 0) {
                points --;
                P += tokens[j--];
            } else break;
        }
        return res;
    }
}