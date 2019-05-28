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
    //Best solution, O(nk), k is the leaves count, or means number of recursive calls, 
    //worst case, k = n
/*
Greedy thinking. 
from 0->i substring, we can only have two cases
1. valid, means opened >=0 means each position >=0, then it is valid, add to result
 another case is opened > 0, means we have extra (, 
((), this will be first valid case, and we will reverse the string to )((, note par also change,
so it will remove middle (, and become )(, last it will reverse again to become the answer

() valid case, it will be reversed again and get correct results

2.invalid cases
()), 
()()), we can remove s[1] or s[3], 

 */
    public static List<String> removeInvalidParentheses_Best(String s) {
        List<String> res = new ArrayList<>();
        char[] par = new char[]{'(', ')'};
        helper(s, res, par, 0, 0);
        return res;
    }

    public static void helper(String s, List<String> res, char[] par, int last_r, int last_l) {
        int opened = 0;
        int r = last_r;
        while (r < s.length() && opened >= 0) {
            if (s.charAt(r) == par[0]) opened++;
            if (s.charAt(r) == par[1]) opened--;
            r++;
        }

        if (opened >= 0)  {
            // no extra ')' is detected. We now have to detect extra '(' by reversing the string.
            String reversed = new StringBuffer(s).reverse().toString();
            //(() case, first recursive
            if (par[0] == '(') helper(reversed, res, new char[]{')', '('}, 0, 0);
            //)( with ) (, after reverse, then it would be ()
            else res.add(reversed);
        } else {  // extra ')' is detected and for substring 0->r, we greedy to get correct string
            r -= 1; // 'i-1' is the index of abnormal ')' which makes count<0
            // to_del is the character to be removed
            for (int l = last_l; l<= r; l++) {
                //()()), for last 2 )), we only want to remove first ), and no dup in results
                //
                if (s.charAt(l) == par[1] && (l == last_l || s.charAt(l-1) != par[1])) {
                    helper(s.substring(0, l) + s.substring(l+1, s.length()), res, par, r, l);
                }
            }
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
   
    //for time & space complextiy:
    //Time: n = s.length(), so isValidParentheses is O(n), 
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
            //stop adding more into queue
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
    
    //verify the s is valid parentheses or not
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
        //List<String> res = tt.removeInvalidParentheses_Best("(()()((()");
        List<String> res = tt.removeInvalidParentheses_Best("(()");
        System.out.println(res);
    }
}
