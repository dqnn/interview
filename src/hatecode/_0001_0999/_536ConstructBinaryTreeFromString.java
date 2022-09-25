package hatecode._0001_0999;
import java.util.*;

public class _536ConstructBinaryTreeFromString {
/*
536. Construct Binary Tree from String
The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parentheses. The integer represents the root's value and a pair of parentheses contains a child binary tree with the same structure.
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
*/
    //thinking process: O(n)/O(n)
    //so the difference between this question and decode string is decode string requires the output of string and here
    //we only need to return the root node, 
    
    //note, when we extract numbers from string, we need to 
    //move i to j - 1 after the j finalized because they already visited
    public TreeNode str2tree(String s) {
        if(s == null || s.length() < 1) return null;
        
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <='9' || c =='-') {
                int j = i + 1;
                while(j < s.length() && s.charAt(j) <='9' && s.charAt(j) >='0') {
                    j++;
                }
                int val = Integer.valueOf(s.substring(i, j));
                TreeNode cur = new TreeNode(val);
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left !=null) parent.right = cur;
                    else parent.left = cur;
                }
                stack.push(cur);
                i = j - 1;
                
            } else if (c ==')') {
                stack.pop();
            }
        }
        
        return stack.isEmpty()? null : stack.peek();
    }
    
    
    public TreeNode str2tree_simpler(String s) {
        if (s == null || s.length() < 1) return null;
        
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i< s.length(); i++, j =i) {
            char c = s.charAt(i);
            //this is faster version of parsing the string, the most complete one is decode strings and reverseStrings, 
            if (c == ')') stack.pop();
            //'-' may like 1_000 this is accepted number in java,but not here
            //here '-' means -9, negative number
            else if (c >= '0' && c <= '9' || c == '-') {
                while(i + 1 < s.length() && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') i++;
                TreeNode cur = new TreeNode(Integer.valueOf(s.substring(j, i+1)));
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left != null) parent.right = cur;
                    else parent.left = cur;
                }
                stack.push(cur);
            }
        }
        return stack.isEmpty() ? null: stack.peek();
    }
}