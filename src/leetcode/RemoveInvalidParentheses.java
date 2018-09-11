package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RemoveInvalidParentheses
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 301. Remove Invalid Parentheses
 */
public class RemoveInvalidParentheses {
    /**
     * Remove the minimum number of invalid parentheses in order to make the input string valid.
     * Return all possible results.

     Note: The input string may contain letters other than the parentheses ( and ).

     Examples:
     "()())()" -> ["()()()", "(())()"]
     "(a)())()" -> ["(a)()()", "(a())()"]
     ")(" -> [""]
     Credits:
     Special thanks to @hpplayer for adding this problem and creating all test cases.

     time : 不知道
     space : O(n)



     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, 0, 0, new char[]{'(', ')'});
        return res;
    }
    public void helper(List<String> res, String s, int last_i, int last_j, char[] pair) {
        for (int count = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == pair[0]) count++;
            if (s.charAt(i) == pair[1]) count--;
            if (count >= 0) continue;
            for (int j = last_j; j <= i; j++) {
                // if s[j] = ')' and first character or one before not equals )
                if (s.charAt(j) == pair[1] && (j == last_j || s.charAt(j - 1) != pair[1])) {
                    // we remove ')' and use left chars from [j, i] to 
                    helper(res, s.substring(0, j) + s.substring(j + 1), i, j, pair);
                }
            }
            return;
        }
        // 
        String reversed = new StringBuilder(s).reverse().toString();
        if (pair[0] == '(') {
            helper(res, reversed, 0, 0, new char[]{')', '('});
        } else {
            res.add(reversed);
        }
    }
    
    // DFS and BFS
//https://leetcode.com/problems/remove-invalid-parentheses/discuss/75038/Evolve-from-intuitive-solution-to-optimal-a-review-of-all-solutions
    
  //O(n * 2^n)
    // interview friendly, the question is to find  minimal removal elements & valid parenthese, so 
    // the minimal ops on string is only to remove one element one time. to solve the duplicate one, we 
    // can use set to store string we have visited. 
    
    // first we add s into queue and visited set, then in while loop, we get this out
    // and to test whether if it is valid, if not, then we set up for loop to remove one element each //time
    // and added to queue if not in visited. 
    
    //the key is isFound， the reason why we have isFound is becz 1 we want minimal ops on removing elements, if we can find one string is valid with same length in a tree level, so that's it, in one tree 
    //level, the pattern should be the same, so we just need to stop adding more into the queue. 
    public List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(s);
        visited.add(s);
        boolean isFound = false;
        while(!q.isEmpty()) {
            String temp = q.poll();
            if (isValidParentheses(temp)) {
                res.add(temp);
                isFound = true;
            }
            // this is the key, so we cannot use break since queue may have more qualified strings
            if (isFound) {
                continue;
            }
            
            for(int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) == '(' || temp.charAt(i) == ')') {
                    String str = temp.substring(0, i) + temp.substring(i+1);
                    if (!visited.contains(str)) {
                        visited.add(str);
                        q.offer(str);
                    }
                }
            }
        }
        return res;
        
    }
    public boolean isValidParentheses(String s) {
        int cnt = 0;
        for(char ch : s.toCharArray()) {
            if (ch == '(') cnt++;
            if (ch == ')') cnt--;
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }
    
    public static void main(String[] args) {
        RemoveInvalidParentheses tt =new RemoveInvalidParentheses();
        List<String> res = tt.removeInvalidParentheses("(()()((()");
        System.out.println(res);
    }
}
