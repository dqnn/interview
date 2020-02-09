package hatecode._0001_0999;

import java.util.*;
public class IncreasingOrderSearchTree {
/*
897. Increasing Order Search Tree
Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9  
*/
    public TreeNode increasingBST(TreeNode root) {
        return helper(root, null);
    }
    //the recursive function, from the problem description i can see the most left node will be root,
    //and its parent,its right child ....
    //this will become a recursive process, but simplify what happened for each node, 
    
    //this function means given a root node, and a tail node, we will convert this BST subtree into a linkedlist
    //
    public TreeNode helper(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = helper(root.left, root);
        root.left = null;
        root.right = helper(root.right, tail);
        return res;
    }
    //another version, recursive solution
    TreeNode prev=null, head=null;
    public TreeNode increasingBST_2nd(TreeNode root) {
        if(root==null) return null;   
        increasingBST(root.left);  
        if(prev!=null) { 
            root.left=null; // we no  longer needs the left  side of the node, so set it to null
            prev.right=root; 
        }
        if(head==null) head=root; // record the most left node as it will be our root
        prev=root; //keep track of the prev node
        increasingBST(root.right); 
        return head;
    }

    public TreeNode increasingBST_Iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode(0), p = dummy; 
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode cur = stack.pop();
                root = cur.right;
                cur.left = null;
                p.right = cur;
                p = p.right;
            }
        }
        return dummy.right; 
    }
}