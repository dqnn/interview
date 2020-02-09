package hatecode._0001_0999;

import java.util.*;
public class GoogleGoBoundaryDectection {
/*
 * tags:google, game
 * Googl interview question:
 * Problem statement:
 * 输入出任意 一个 棋盘位置， 判断是否那个位置的棋子处于被包围状 
态-上下左右都是不同颜色地棋子。被包围的有可能是相同颜色地一组棋子，只要给定 
位置属于那个组 也返回true. 
比如 (.代表没有棋子，B代表黑，W代表白) 
..B.. 
.BWB. 
.BWB. 
..B.. 
 */
    //1-black, 2-white, 0-empty (i, j) means the position, color means the 
    public boolean isSurrounded(int[][] g, int i, int j) {
        if (g == null || g.length < 1 || g[0].length < 1) return true;
        int r = g.length, c=g[0].length;
        if (i < 0 || i >= r || j < 0 || j >= c || g[i][j] == 0) return false;
        //1 visited, do not know result
        //2 visited, result is it is surrounded
        //3 visited, result is not surrounded
        int[][] visited = new int[r][c];
        visited[i][j] = 1;
        return helper(g, visited, i, j, g[i][j]);
    }
    //there were 1 edge question:
    //suppose color is white, so if white on edge are considered surrounded, should not
    //
    private boolean helper(int[][] g, int[][] visited, int i, int j, int color) {
        if (visited[i][j] == 1 || g[i][j] == 0) return false;
        visited[i][j] = 1;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for(int[] dir: dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >=0 && ni < g.length && nj >= 0 && nj < g[0].length) {
                if (helper(g, visited, ni, nj, color)) {
                    return true;
                }
            }
        }
        //tried 
        return false;
    }
    
    //google interview doc, not from LC
    /*
     * problem statement, chess on binary tree,
     * 两个人红蓝，在二叉树上，每个人可以从第一个选的点开始同时往相邻的点扩展占领点，已知red选了一个点，（
     * 规则大概是两点之间的可以共同占领，但红的children只能红的占）问蓝第一个点选哪里最后能占领的最多。 API:
     * 输入root和红的点，输出蓝色选的node follow up是怎么更efficient 选第一个点的的时候，要选一个三部分尽量均匀的， 即
     * 任何两部分都大于第三部分的，即红色先选并选第一个点的规则
     * 
     * thinking process:
     * 
     * 我觉得这道题是这个思路： 一棵二叉树，如果我的对手已经选中了某个节点node，接下来如果把这个节点从树中移除，那么这棵树会裂解成三个connected
     * components: 
     * A: node的left child 
     * B: node的right child 
     * C:其他节点（如果node是root那这部分为空，否则这部分就是node的parent node及与其相连的节点们）
     * 
     * 接下来我的选择就是在这个三个components中选择一个component作为我的领地，阻止对手在这个component扩张。
     * 为了最大化我的优势，我当然应该选择其中节点个数最多的。那么接下来我能不能赢得游戏就很好判断了：
     * 
     * 如果其中存在某个component的节点数超过了原来整棵树的节点数的一半，那么我就可以选择这个component，然后我就赢了；否则我必输，
     * 因为即使我选择了节点最多的那个component，我和其他两个components之间被我的对手阻隔着，无法扩张到那里。
     * 
     * 所以这题的解法就是数出ABC三个部分的节点数，最后return MAX(size(A), size(B), size(C)) > size(the
     * whole tree) / 2
     */
    
    class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        public TreeNode(int key) {this.key = key;}
    }
    private TreeNode redParent = null;
    private TreeNode red = null;
    public TreeNode findNode(TreeNode root, TreeNode red) {
        // sanity check
        this.red = red;
        int redL = countNode(red.left);
        int redR = countNode(red.right);
        int redUp = countNode(root);
        int max = Math.max(redUp, Math.max(redL, redR));
        if(max == redL) return red.left;
        else if(max == redR) return red.right;
        else return root;
    }
    private int countNode(TreeNode root) {
        if(root == null) return 0;
        if(root.left == red || root.right == red) redParent = root;
        if(root == red) return 0;
        return countNode(root.left) + 1 + countNode(root.right);
    }
}
