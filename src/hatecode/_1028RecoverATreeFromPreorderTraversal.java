package hatecode;
import java.util.*;
public class _1028RecoverATreeFromPreorderTraversal {
/*
1028. Recover a Tree From Preorder Traversal
We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is 
the depth of this node), then we output the value of this node.  
(If the depth of a node is D, the depth of its immediate child 
is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be 
the left child.

Given the output S of this traversal, recover the tree and 
return its root.

Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
*/
    
    //thinking process: O(n)/O(n) n = s.length()
    //given a string, preorder visit this tree, and we form a dash as level, 
    //level 1 is 1 dash, level 2 is two dashes, if there is only node, that node is 
    //guarnteed as left child,
    
    //
    public TreeNode recoverFromPreorder_Best(String s) {
        if(s == null || s.length() < 1) return null;
        int level = 0, var = 0;
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0; i < s.length();) {
            for(level = 0;s.charAt(i) == '-';i++) level++;
            for (var = 0; i < s.length() && s.charAt(i) != '-'; i++) {
                var = var * 10 + (s.charAt(i) - '0');
            }
            while(stack.size() > level) {
                stack.pop();
            }
            TreeNode node = new TreeNode(var);
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.add(node);
        }
        
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.pop();
    }
    
    
    int index = 0;
    public TreeNode recoverFromPreorder(String S) {
        return helper(S, 0);
    }
    
    public TreeNode helper(String s, int depth) {
        int numDash = 0;
        while (index + numDash < s.length() && s.charAt(index + numDash) == '-') {
            numDash++;
        }
        if (numDash != depth) return null;
        int next = index + numDash;
        while (next < s.length() && s.charAt(next) != '-') next++;
        int val = Integer.parseInt(s.substring(index + numDash, next));
        index = next;
        TreeNode root = new TreeNode(val);
        root.left = helper(s, depth + 1);
        root.right = helper(s, depth + 1);
        return root;
    }
}