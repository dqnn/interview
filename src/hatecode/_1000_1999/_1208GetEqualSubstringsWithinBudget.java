package hatecode._1000_1999;
public class _1208GetEqualSubstringsWithinBudget {
/*
1208. Get Equal Substrings Within Budget
You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.

You are also given an integer maxCost.

Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.

If there is no substring from s that can be changed to its corresponding substring from t, return 0.

 

Example 1:

Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
*/
    //thinking process: O(n)/O(1)
    //the problem is given two strings, s and t, and an integer cost, we want to 
    //get same string s->t, starts from first char, cost[i] = |s[i]-t[i]|, but we 
    //cannot exceed the maxCost
    
    //so return the max length substring which they at least s can match first chars of t
    
    //so since it has to start from first char, we just have to replace each char
    //and that's the least cost, so the problem become a sliding window problem,
    //image a window upon this sting like a ruler, if cost within window <= cost then we 
    //can move right pointer until cost is bigger, if it is bigger then we move left 
    //pointer
    //each move we will have a window length, then we can use for max compare
    
    //improvement:
    //since it is always increasing, see simplifed code. 
    
    //another solution is prefix sum and binary search, 
    //find two number max diff less than cost and it has longest length
    //since prefix sum are always increasing since it is abs cost > 0. then we can use 
    //binary seach to look for smallest number for each one
    
    
    public int equalSubstring(String s, String t, int maxCost) {
        if (s == null || t == null || s.length() != t.length() || maxCost < 0) {
            return 0;
        }
        
        int win = 0;
        for(int r = 0, l = 0; r < s.length(); r++) {
            maxCost -= Math.abs(s.charAt(r) - t.charAt(r));
            while (maxCost < 0) {
                maxCost += Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }
            win = Math.max(win, r - l + 1);
        }
        return win;
    }
    
    //TODO: understand why it indicate r = n means the max window
    public int equalSubstring_Simplified(String s, String t, int maxCost) {
        // sanity check
        if(s == null || s.isEmpty() || t == null || t.isEmpty() 
                || s.length() != t.length()) return 0;
        
        final char[] chss = s.toCharArray();
        final char[] chst = t.toCharArray();
        
        int l = 0, r = 0;
        while(r < s.length()){
            maxCost -= Math.abs(chss[r] - chst[r]);

            if(maxCost < 0){
                maxCost += Math.abs(chss[l] - chst[l]);
                l++;
            }
            r++;
        }
        
        return r - l;
    }
}