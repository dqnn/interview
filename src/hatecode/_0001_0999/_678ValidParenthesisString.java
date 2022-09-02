package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _678ValidParenthesisString {
/*
678. Valid Parenthesis String
Given a string containing only three types of characters: '(', ')' and '*', 
write a function to check whether this string is valid. We define the validity 
of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis 
'(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
*/

    //interview friendly:
    //cmax max number of ( could be paired,
    //cmin min number of ( must be paired
    
    //a string is valid: 1: cmax >=0, 2 cmin = 0 in the end for every position
    public boolean checkValidString_Interviewe_Friendly(String s) {
        int cmin = 0, cmax = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                cmax++;cmin++;
            } else if (c == ')') {
                cmax--;
                cmin = Math.max(cmin - 1, 0);
            } else {
                cmax++;
                cmin = Math.max(cmin - 1, 0);
            }
            if (cmax < 0) return false;
        }
        return cmin == 0;
    }   
    
    
    
 /*
 The idea is to similar to validate a string only contains '(' and ')'. 
 But extend it to tracking the lower and upper bound of valid '(' counts. 
 My thinking process is as following.

scan from left to right, and record counts of unpaired ‘(’ for all possible cases. 
For ‘(’ and ‘)’, it is straightforward, just increment and decrement all counts respectively.
When the character is '*', there are three cases, 
‘(’, empty, or ‘)’, 
we can think those three cases as three branches in the ongoing route.
Take “(**())” as an example. There are 6 chars:
----At step 0: only one count = 1.
----At step 1: the route will be diverted into three branches.
so there are three counts: 1 - 1, 1, 1 + 1 which is 0, 1, 2, for ‘)’,
 empty and ‘(’ respectively.
----At step 2 each route is diverged into three routes again. 
so there will be 9 possible routes now.

--  For count = 0, it will be diverted into 0 – 1, 0, 0 + 1, which is -1, 0, 1, 
but when the count is -1, that means there are more ‘)’s than ‘(’s, 
and we need to stop early at that route, since it is invalid. we end with 0, 1.
--  For count = 1, it will be diverted into 1 – 1, 1, 1 + 1, which is 0, 1, 2
--  For count = 2, it will be diverted into 2 – 1, 2, 2 + 1, which is 1, 2, 3
To summarize step 2, we end up with counts of 0,1,2,3
----At step 3, increment all counts --> 1,2,3,4
----At step 4, decrement all counts --> 0,1,2,3
----At step 5, decrement all counts --> -1, 0,1,2, the route with count -1 is invalid, 
so stop early at that route. Now we have 0,1,2.
In the very end, we find that there is a route that can reach count == 0. 
Which means all ‘(’ and ‘)’ are cancelled. So, the original String is valid.
Another finding is counts of unpaired ‘(’ for all valid routes are consecutive integers. 
So we only need to keep a lower and upper bound of that consecutive integers to save space.
One case doesn’t show up in the example is: if the upper bound is negative, that means 
all routes have more ‘)’ than ‘(’ --> all routes are invalid --> stop and return false.

   
 */
    //O(n) O(1)
    public boolean checkValidString5(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            //process * 
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
    
    /*
How to check valid parenthesis w/ only ( and )? Easy. Count each char from left to right. 
When we see (, count++; when we see ) count--; if count < 0, it is invalid () is more than (); 
At last, count should == 0.
This problem added *. The easiest way is to try 3 possible ways when we see it. Return true 
if one of them is valid.
   */
    public boolean checkValidString4(String s) {
        return check(s, 0, 0);
    }
    
    private boolean check(String s, int start, int count) {
        if (count < 0) return false;
        
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            else if (c == ')') {
                if (count <= 0) return false;
                count--;
            }
            else if (c == '*') {
                return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
            }
        }
        
        return count == 0;
    }
    /*
Let lo, hi respectively be the smallest and largest possible number of open left 
brackets after processing the current character in the string.

If we encounter a left bracket (c == '('), then lo++, otherwise we could write a 
right bracket, so lo--. If we encounter what can be a left bracket (c != ')'), 
then hi++, otherwise we must write a right bracket, so hi--. If hi < 0, then 
the current prefix can't be made valid no matter what our choices are. Also, 
we can never have less than 0 open left brackets. At the end, we should check 
that we can have exactly 0 open left brackets.
    */
    //Best O(n), O(1) this sems left scan and right scan
    public boolean checkValidString(String s) {
       int lo = 0, hi = 0;
       for (char c: s.toCharArray()) {
           lo += c == '(' ? 1 : -1;
           hi += c != ')' ? 1 : -1;
           if (hi < 0) break;
           lo = Math.max(lo, 0);
       }
       return lo == 0;
    }
    
    //DP version, O(n^3)/O(n^2)
/*
Let dp[i][j] be true if and only if the interval s[i], s[i+1], ..., s[j] 
can be made valid. Then dp[i][j] is true only if:

s[i] is '*', and the interval s[i+1], s[i+2], ..., s[j] can be made valid;

or, s[i] can be made to be '(', and there is some k in [i+1, j] such that s[k] can 
be made to be ')', plus the two intervals cut by s[k] (s[i+1: k] and s[k+1: j+1]) 
can be made valid;
*/
    public boolean checkValidString3(String s) {
        int n = s.length();
        if (n == 0) return true;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') dp[i][i] = true;
            if (i < n-1 &&
                    (s.charAt(i) == '(' || s.charAt(i) == '*') &&
                    (s.charAt(i+1) == ')' || s.charAt(i+1) == '*')) {
                dp[i][i+1] = true;
            }
        }

        for (int size = 2; size < n; size++) {
            for (int i = 0; i + size < n; i++) {
                if (s.charAt(i) == '*' && dp[i+1][i+size] == true) {
                    dp[i][i+size] = true;
                } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                    for (int k = i+1; k <= i+size; k++) {
                        if ((s.charAt(k) == ')' || s.charAt(k) == '*') &&
                                (k == i+1 || dp[i+1][k-1]) &&
                                (k == i+size || dp[k+1][i+size])) {
                            dp[i][i+size] = true;
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }
    
    //TLE brute force
    public boolean checkValidString1(String s) {
        if (s == null || s.trim().equals("")) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < s.length();i++) {
            if (s.charAt(i) == '*') {
                list.add(i);
            }
        }
        
        char[] flags = {'s', '(', ')'};
        return helper(s, list, 0, flags); 
    }
    private boolean helper(String s, List<Integer> list, int start, char[] flags) {
        if (s.indexOf("*") < 0 || start == list.size()) {
            return isValid(s);
        }
        boolean res = false;
        for(int i = start; i < list.size();i++) {
            for(char f : flags) {
                String temp = s.substring(0, i) + f + s.substring(i+1);
                if (helper(temp, list, i+1, flags)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(String s) {
        System.out.println(s);
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()) {
            if (ch == 's') continue;
            if (ch == '(') {
                stack.push(')');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
