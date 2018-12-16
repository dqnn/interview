package hatecode;

import java.util.ArrayList;
import java.util.Collections;
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
    
  // Time： O(n * 2^n) Space O(2^n)
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
            // this is the key, so we cannot use break since queue may have more qualified strings,
            //for that level, because if we go deeper, that;s not minial removal of ( or )
            if (isFound) {
                continue;
            }
            // for loop we would like to make it simple
            for(int i = 0; i < temp.length(); i++) {
                //we will ignore other chars since we only focus on ( )
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
    
    //this is for just reference, but this solution remove some duplicates 
    //case 1: "())" ---> "()"  only remove the first one of '))'
    /*case 2: 
     * we need remove 2 from "(())(("
       we want to remove positions combination i,j with no duplicate
    so we let i < j then it will not generate duplicate combinations
    in practice, we record the position i and put it in to queue
    which is then polled out and used as the starting point of the next removal
     */
    //case 3: 
    /*
     A third observation is if the previous step we removed a "(", we should never remove a ")" in the following steps. 
     This is obvious since otherwise we could just save these two removals and still be valid with less removals. 
     With this observation all the possible removals will be something like this
     ")))))))))((((((((("
     All the removed characters forming a string with consecutive left bracket followed by consecutive right bracket.
     By applying these restrictions, we can avoid generate duplicate strings and the need of a set which saves 
     a lot of space.
     */
    public List<String> removeInvalidParentheses3(String s) {
        if (isValid(s))
            return Collections.singletonList(s);
        List<String> ans = new ArrayList<>();
        //The queue only contains invalid middle steps
        Queue<Tuple> queue = new LinkedList<>();
        //The 3-Tuple is (string, startIndex, lastRemovedChar)
        queue.add(new Tuple(s, 0, ')'));
        while (!queue.isEmpty()) {
            Tuple x = queue.poll();
            //Observation 2, start from last removal position
            for (int i = x.start; i < x.string.length(); ++i) {
                char ch = x.string.charAt(i);
                //Not parentheses
                if (ch != '(' && ch != ')') continue;
                //Observation 1, do not repeatedly remove from consecutive ones
                if (i != x.start && x.string.charAt(i - 1) == ch) continue;
                //Observation 3, do not remove a pair of valid parentheses
                if (x.removed == '(' && ch == ')') continue;
                String t = x.string.substring(0, i) + x.string.substring(i + 1);
                //Check isValid before add
                if (isValid(t))
                    ans.add(t);
                //Avoid adding leaf level strings
                else if (ans.isEmpty())
                    queue.add(new Tuple(t, i, ch));
            }
        }
        return ans;
    }

    public static boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') ++count;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }

    private class Tuple {
        public final String string;
        public final int start;
        public final char removed;

        public Tuple(String string, int start, char removed) {
            this.string = string;
            this.start = start;
            this.removed = removed;
        }
    }
    
    public static void main(String[] args) {
        RemoveInvalidParentheses tt =new RemoveInvalidParentheses();
        List<String> res = tt.removeInvalidParentheses("(()()((()");
        System.out.println(res);
    }
}
