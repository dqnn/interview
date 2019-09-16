package hatecode;
public class _998MaximumBinaryTreeII {
/*
998. Maximum Binary Tree II
We are given the root node of a maximum tree: a tree where every node has a value greater than any other 
value in its subtree.

Just as in the previous problem, the given tree was constructed from an list A (root = Construct(A)) recursively with the following Construct(A) routine:

If A is empty, return null.
Otherwise, let A[i] be the largest element of A.  Create a root node with value A[i].
The left child of root will be Construct([A[0], A[1], ..., A[i-1]])
The right child of root will be Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
Return root.
Note that we were not given A directly, only a root node root = Construct(A).

Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.

Return Construct(B).
Example 1:
Input: root = [4,1,3,null,null,2], val = 5
Output: [5,4,null,1,3,null,null,2]
Explanation: A = [1,4,2,3], B = [1,4,2,3,5]
*/
    //O(n)/O(n)
    //insert a node into a maximum tree， given a tree and a value, 
    //insert the value as a node into the max tree, the rules are the same as 654
    
    //max tree is root is greater than any child, 
    
    //thinking process:
    
    //all tree problems can be solved by recursive or iterative with a stack, here first we try 
    //recursive, 
    
    //since last element is appended to the array,so if it is smaller than root, then it should be 
    //in right subtree, else it should be new root, and previous root should be its left child
    public TreeNode insertIntoMaxTree_Recursive(TreeNode root, int val) {
        if (root != null && root.val > val) {
            root.right = insertIntoMaxTree_Recursive(root.right, val);
            return root;
        }
        //if biggest one, then we should mark this node as new root
        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;
    }
    
    //O(n)/O(1)
    //
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val), cur = root;
        if (root.val < val) {
            node.left = root;
            return node;
        }
        while (cur.right != null && cur.right.val > val) {
            cur = cur.right;
        }
        node.left = cur.right;
        cur.right = node;
        return root;
    }
}