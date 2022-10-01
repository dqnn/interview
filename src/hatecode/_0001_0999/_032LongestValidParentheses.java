package hatecode._0001_0999;
import java.util.*;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestValidParentheses
 * Date : Dec, 2017
 * Description : 32. Longest Valid Parentheses
 */
public class _032LongestValidParentheses {
    /**
     Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"


     0 1 2 3 4 5
     ( ( ) )

     time : O(n)
     space : O(n)


     * @param s
     * @return
     */
    //")()(()))" should be good example
    /*
     *  )  --->  prev = 0, stack = []  res = min
     *  ( ---->  prev = 0  stack = [1] res = min
     *  ) ---->  prev = 0  stack = []  res = 2-0= 2
     *  ( ---->  prev = 0  stack=[3]   res = 2
     *  ( ---->  prev = 0  stack=[3,4]  res = 2
     *  ) ---->  prev = 0  stack=[3]   res = max(2, 5-3) = 2
     *  ) ---->  prev = 0, stack=[]    res = max(2, 6-0) = 6
     *  ) ---->  prev = 7  stack=[]    res = 6
     *  
     */
    
    
    //thinking process: interview friendly: 
    //this problem provide a thinking that for palindrom related input, stack always can be an option, 
    //because we can always find the match and  we can reset the counter, here like start to re-describe 
    //the substring
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        //start = -1, because length of string is end-start + 1, but considering "()" you will 
        //need -1 to make the length as 2, also for "(()))" for last ")" it will be a new start
        //for next valid parentheses, so in this problem we always make start as -1 is better
        int prev = -1, res = 0;
        for(int i = 0; i< s.length(); i++) {
            //System.out.println(stack);
            if (s.charAt(i) == '(')  stack.push(i);
            else {
                // so if stack is empty, then we know we already matched all "C", so current i as idx 
                // the position in S is end pointer, 
                //means we need to start new round of matching or just end
                if (stack.isEmpty())  prev = i;
                else {
                    // so we meet ")" and stack is not empty which means it has 1 "(" at least
                    //if stack is not empty, means we still have "C" not matched, so we continue to move and  
                    // use top in stack as start pointer to calc the length of the qualified string
                    stack.pop();
                    // this is if stack is not empty, we calculate from the current top ( idx
                    //start last time which it is legal
                    res = Math.max(res, stack.isEmpty() ? i - prev : i - stack.peek());
                }
            }
        }
        return res;
    }
    /*
     * My solution uses DP. The main idea is as follows: I construct a array longest[], for any longest[i], 
     * it stores the longest length of valid parentheses which is end at i.

And the DP idea is :

If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.

Else if s[i] is ')'

     If s[i-1] is '(', longest[i] = longest[i-2] + 2

     Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', 
     longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]

For example, input "()(())", 
at i = 5, longest array is [0,2,0,0,2,0], longest[5] = longest[4] + 2 + longest[1] = 6.
     */
    //dp[i] means the the max string len of valid parenthese ends in s[i], so 
    //s[i] = '(' this is not true, so dp[i] = 0
    //s[i] = ')', if s[i-1] = '(', then dp[i] = dp[i-1] + 2 because () has length of 2
    //            if s[i-1] = ')', so we need to look back to find the the valid parenthese which
    //ends i - dp[i], here needs one example "()(())",so when i = 5, s[4] = ')',so 
    //we need to look back for valid parenthese which can be a longer string, so we should jump back to
    // i = 1, now we are at i=5, so dp[i] = 4, so   5 - 4 = 1
    public static int longestValidParentheses2(String s) {
        if (s == null || s.length() < 1) return 0;
        int n = s.length();
        int[] dp = new int[n];
        int res = 0, left = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') left++; 
            else if (left > 0){
                // we need to add dp[i-1] first, because then we can jump back to most 
                //earliest index so that we can connect them
                //(()) or () case
                 dp[i] = dp[i-1] + 2;
                 dp[i] += (i - dp[i] >= 0) ? dp[i-dp[i]] : 0;
                 res = Math.max(res, dp[i]);
                 left--;
            }
        }
        System.out.println(Arrays.toString(dp));
        return res;
    }
    
    public int longestValidParentheses_SameDP(String s) {
        if(s == null || s.length() <= 1) return 0;
        int max = 0;
        int[] dp = new int[s.length()];
        for(int i=1; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                if(s.charAt(i-1) == '('){
                    dp[i] = (i-2) >= 0 ? (dp[i-2] + 2) : 2;
                    max = Math.max(dp[i],max);
                } else { // if s[i-1] == ')', combine the previous length.
                    if(i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '('){
                        dp[i] = dp[i-1] + 2 + ((i-dp[i-1]-2 >= 0)?dp[i-dp[i-1]-2]:0);
                        max = Math.max(dp[i],max);
                    }
                }
            }
            //else if s[i] == '(', skip it, because longest[i] must be 0
        }
        return max;
    }
    
    public static void main(String[] args) {
        System.out.println(longestValidParentheses2("((())((()))"));
    }
}
