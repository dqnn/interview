package hatecode;
public class SplitBST {
/*
776. Split BST
Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree contains a node with value V.

Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.

You should output the root TreeNode of both subtrees after splitting, in any order.

Example 1:

Input: root = [4,2,6,1,3,5,7], V = 2
Output: [[2,1],[4,3,6,null,null,5,7]]
*/
    // give  a binary node, want to split the tree into two subtrees
    // if we can find one integer eauls to the V, that's the best case
    //and if we cannot then we have to find the to keep the most 
    //part not change and change the smaller tree, so we want 
    //a structure which could help to keep the tree same structure while 
    //we visit the tree, and at the same time we the node which equals 
    //or most close we want
    //it is like the dummy node in linked list, which could help to prevent null
    public static TreeNode[] splitBST2(TreeNode root, int V) {
        // to store the nodes which are smaler than V
        TreeNode dummySm = new TreeNode(0);
        TreeNode curSm = dummySm;
        // to store the nodes which are larger than V
        TreeNode dummyLg = new TreeNode(0);
        TreeNode curLg = dummyLg;
        while (root != null) {
            if (root.val <= V) {
                curSm.right = root;
                curSm = root;
                root = root.right;
                curSm.right = null;
            } else {
                curLg.left = root;
                curLg = root;
                root = root.left;
                curLg.left = null;
            }
        }
        return new TreeNode[] {
                dummySm.right, dummyLg.left };
    }
    
   // will change the tree structure if needed
    // balanced O(lgn) if not O(n)
    public TreeNode[] splitBST(TreeNode root, int V) {
        if(root==null) return new TreeNode[]{null, null};
        
        TreeNode[] splitted;
        if(root.val<= V) {
            splitted = splitBST(root.right, V);
            root.right = splitted[0];
            splitted[0] = root;
        } else {
            splitted = splitBST(root.left, V);
            root.left = splitted[1];
            splitted[1] = root;
        }
        
        return splitted;
    }
        
}