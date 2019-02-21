package hatecode;

public class InsertIntoABinarySearchTree {
/*
701. Insert into a Binary Search Tree
Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
*/
    public TreeNode insertIntoBST_recursive(TreeNode root, int val) {
        if (root == null) return root;
        helper(root, val);
        return root;
    }
    
    public void helper(TreeNode node, int val) {
        if (node == null) return;
        if (node.val >= val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
                //return;
            } else {
                helper(node.left,val);
            }
        } else {
             if (node.right == null) {
                 node.right = new TreeNode(val);
                 //return;
            } else {
                 helper(node.right, val);
             }
        }
    }
    
    //iterative version
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode cur = root;
        while(true) {
            if(cur.val <= val) {
                if(cur.right != null) cur = cur.right;
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if(cur.left != null) cur = cur.left;
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}