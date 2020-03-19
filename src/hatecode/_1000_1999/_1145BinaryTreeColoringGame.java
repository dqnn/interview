package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;

public class _1145BinaryTreeColoringGame {
/*
1145. Binary Tree Coloring Game
Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.

Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.

Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)

If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.

You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
Output: true
*/
    //thinking process: O(n)/O(h)
    
    //the problem is to say: given a complete binary tree, n(odd) nodes, each node 
    //has different distinct value, two players, x is one value, each player 
    //can choose its adjacent nodes, one player will win if he has more nodes, suppose you
    //are 2nd player, return true if you can win else false
    
    //
    int l, r, val;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        val = x;
        count(root);
        return Math.max(Math.max(l, r), n - l - r - 1) > n / 2;
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        int lcnt = count(node.left), rcnt = count(node.right);
        if (node.val == val) {
            l = lcnt;
            r = rcnt;
        }
        return lcnt + rcnt + 1;
    }
}