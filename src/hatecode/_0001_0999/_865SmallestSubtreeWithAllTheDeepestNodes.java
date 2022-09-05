package hatecode._0001_0999;
public class _865SmallestSubtreeWithAllTheDeepestNodes {
/*
 * tag: tree, dfs, lowest ancestor
865. Smallest Subtree with all the Deepest Nodes
Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.


*/
    
    class Result {
    TreeNode node;
    int dist;
    Result(TreeNode n, int d) {
        node = n;
        dist = d;
    }
}
    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        return dfs(root).node;
    }

    // Return the result of the subtree at this node.
    public Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);
        Result L = dfs(node.left),
               R = dfs(node.right);
        if (L.dist > R.dist) return new Result(L.node, L.dist + 1);
        if (L.dist < R.dist) return new Result(R.node, R.dist + 1);
        return new Result(node, L.dist + 1);
    }
    
    
    class Pair {
        int dist;
        TreeNode node;
        public Pair(int dist, TreeNode node) {
            this.dist = dist;
            this.node =node;
        }
    }
    
    //same as above, but code is more cleaner, this is the same as 
    //lowest ancestors
    //we need to get the common node when d1 = d2, if not try to deeper
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deep(root).node;
    }

    public Pair deep(TreeNode root) {
        if (root == null) return new Pair(0, null);
        Pair l = deep(root.left), r = deep(root.right);

        int d1 = l.dist, d2 = r.dist;
        int newDist = Math.max(d1, d2) + 1;
        TreeNode commonNode = d1 == d2 ? root : d1 > d2 ? l.node : r.node;
        return new Pair(newDist, commonNode);
    }

}