package hatecode._0001_0999;

public class _510InorderSuccessorInBSTII {
/*
285. Inorder Successor in BST
510. Inorder Successor in BST II
Given a binary search tree and a node in it, 
find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.
*/
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    
    //thinking process: given a Node in BST, find the smallest successor in the tree. 
  //so inorder visit, its right child will be its next successor, so we focuse on right child
    // if right child is null which means the successor is in parent, and still we compare to parent value < x.val 
    //then it means we need to climb to the parent node which has smallest node here:
    //1 is in left child branch, then there will be one answer
    /*
     *               5
     *              /  \
     *             3
     *            / \
     *           1   x(4)
     *           
     * as above, if x's successor will be 5, 

     *                   
     */
    //2 is x already the most right leaf node, then it would be null
    
    //3  if its right not null, then we looks for its most left
    public Node inorderSuccessor(Node x) {
        if (x == null) return null;
        
        if (x.right == null) {
            Node res = x.parent;
            while(res != null && res.val < x.val) {
                res = res.parent;
            }
            return res;
        } else {
            Node res = x.right;
            while(res.left != null) {
                res = res.left;
            }
            return res;
        }
    }
}