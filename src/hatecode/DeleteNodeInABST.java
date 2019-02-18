package hatecode;
import java.util.*;
public class DeleteNodeInABST {
/*
450. Delete Node in a BST
*/
    //this is a question not easy to think about. 
    
    //two cases, one is the root, another one is not the root, but even the root the code can be written in recursive way
    //
    //the way how we thinking of tree is always considering node as root node, so if it is root node(must be), then 
    //we have to remove the root and return its child, 3 cases
    //1. left is null return right
    //2. right is null, return left
    //3. both child there then we need to reconstruct the tree, we get min valie from its right tree to be copied to the root
    //and remove the node  from right branch, left tree still there
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (key < root.val) root.left = deleteNode(root.left, key);
        else if (key > root.val) root.right = deleteNode(root.right, key);
        else {
            //left can be remove because we there is no change on the left
            /*
            if (root.left == null) return root.right;
            else 
            */
            if (root.right == null) return root.left;
            
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        
        return root;
    }
    
    private TreeNode findMin(TreeNode node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}