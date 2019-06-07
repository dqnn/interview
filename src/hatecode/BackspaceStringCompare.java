package hatecode;

import java.util.Stack;

public class BackspaceStringCompare {
/*
844： Back space String Compare
Given two strings S and T, return if they are equal when both are typed into empty text editors. 
# means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
 */
    //thinking process: so we want to compare two strings, and if it contains #, then previous char
    //could be removed, else will be kept, compare the final string, if the same, return true or false
    
    //we use skipS or skipT to indicate how many chars we can scan from right to left
    public boolean backspaceCompare(String S, String T) {
        if (S == null && T == null) {
            return true;
        }
        if (S == null || T == null) {
            return false;
        }
        int i = S.length() - 1, j = T.length() -1;
        int skipS = 0, skipT = 0;
        while(i >=0 || j>=0) {
            //i would stop at the string where it cannot move to left
            while(i >=0) {
                if (S.charAt(i) == '#') {
                    skipS ++;
                    i--;
                } else if (skipS > 0) {
                    skipS --;
                    i--;
                } else break;
            }
            //j is the same as i
            while(j >=0) {
                if (T.charAt(j) == '#') {
                    skipT ++;
                    j--;
                } else if (skipT > 0) {
                    skipT --;
                    j--;
                } else break;
            }
            //if they are the same, both scan back
            if (i >= 0 && j >=0 && (S.charAt(i) == T.charAt(j))) {
                i --;
                j --;
            //one case is that i = -1 and j = -1
            //another case here is that i >=0 and j >=0 while they are not the same
            //like "a" and "c"
            } else {
                return (i == -1 && j == -1);
            }
        }
        return true;
    }

    //we want a O(n + m) $$ O(1) solution, so we can only use varibale and one loop, so one trick is that: 
    //interview friendly
    public boolean backspaceCompare3(String S, String T) {
        if (S == null && T == null) {
            return true;
        }
        if (S == null || T == null) {
            return false;
        }
        //b means how many chars we need to skip next lowe char
        for(int i = S.length()-1, j = T.length() -1;;i--,j--) {
            for(int b = 0; i>=0 && (b > 0 || S.charAt(i) == '#');i--) {
                b += S.charAt(i) == '#'? 1 : -1;
            }
            for(int b =0; j>=0 && (b > 0 || T.charAt(j) == '#');j--) {
                b += T.charAt(j) == '#' ? 1 : -1;
            }
            //finally we will be here, since i would decrease always
            //but they should be same, i-- and j-- will continue or 
            // if they are not the same and i and j char will finally 
            //in the result string, then it would false.
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else {
                return i == -1 && j == -1;
            }
        }
    }
    
    //another solution, use a stack to remove previous char
    //O(m+n) && O(m+n)
    public boolean backspaceCompare2(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c: S.toCharArray()) {
            if (c != '#')
                stack.push(c);
            else if (!stack.empty())
                stack.pop();
        }
        return String.valueOf(stack);
    }
    
    //two pointer solutions
    //i point to S back to front, j point to T back to front, 
    //if we meet #, i or j move countS or countT steps to front, 
    //so we check each character in their correct position
    public boolean backspaceCompare_TP(String S, String T) {
        int i = S.length()-1;
        int j = T.length()-1;
        //number of # from back to front
        int countS = 0;
        int countT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (countS > 0 || S.charAt(i) == '#')) {
                if (S.charAt(i) == '#') countS++;
                else countS--;
                i--;
            }
            char left = i < 0 ? '@' : S.charAt(i);
            while (j >= 0 && (countT > 0 || T.charAt(j) == '#')) {
                if (T.charAt(j) == '#') countT++;
                else countT--;
                j--;
            }
            char right = j < 0 ? '@' : T.charAt(j);
            if (left != right) return false;
            i--;
            j--;
        }
        return true;
    }
}