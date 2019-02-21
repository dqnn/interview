package hatecode;
public class DistributeCoinsInBinaryTree {
/*
979. Distribute Coins in Binary Tree
Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.
Input: [3,0,0]
Output: 2
*/
private int moves;
    public int distributeCoins_DFS(TreeNode root) {
        moves=0;
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int res = left+right;
        moves+=Math.abs(left)+Math.abs(right);
        res+=root.val-1;
        return res;
    }
    
    
    int count;
    //interview friendly version
    //1. null node is ok
    //2. leaf if more than 1 then give parents, or get from parents, so for minimal tree it 
    //will be balanced
    public int distributeCoins(TreeNode root) {
        distributeCoinsHelper(root);
        return count;
    }
    
    private int distributeCoinsHelper(TreeNode root){
        if (root == null)
            return 0;
        
        int childRequire = distributeCoinsHelper(root.left) + distributeCoinsHelper(root.right);
        if (root.val == childRequire + 1) {
            // nothing
            return 0;
        } else if (root.val < childRequire + 1) {
            int thisRequire = childRequire + 1 - root.val;
            count += thisRequire;
            return thisRequire;
        } else if (root.val > childRequire + 1) {
            int thisGive = root.val - (childRequire + 1);
            count += thisGive;
            return -thisGive;
        }
        
        return 0;
    }
}