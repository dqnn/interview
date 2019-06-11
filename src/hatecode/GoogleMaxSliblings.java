package hatecode;

import java.util.*;
public class GoogleMaxSliblings {
    /*
     * Goole interview, not from LC Problem statement 
     * 第二题：给一个二叉树，找maximum size of
     * level which has the largest number of
     * siblings。这里sibling的定义就是从这一层的左端点到右端点的所有存在或者不存在的node，
     * 也就是假设这中间的Parent都有左右孩子的情况下，
     * 从左端点到右端点总共有多少个。
     * 
     * 思路： 利用完全二叉树的index性质，按层遍历，记录每层起始node的index和末尾的index，相减后得到的距离，
     * 然后更新最大值
     * 
     */
    class MyNode {
        int      idx;
        TreeNode node;

        public MyNode(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }

    public int maxSiblings(TreeNode root) {
        if (root == null)
            return 0;
        int res = 0;
        Queue<MyNode> queue = new LinkedList<>();
        queue.offer(new MyNode(root, 1));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int first = queue.peek().idx;
            while (size-- > 0) {
                MyNode curr = queue.poll();
                if (curr.node.left != null) {
                    queue.offer(new MyNode(curr.node.left, curr.idx * 2));// 防止溢出的方式就是id*2变成(id - first) * 2，把绝对计算变成相对
                }
                if (curr.node.right != null) {
                    queue.offer(new MyNode(curr.node.right, curr.idx * 2 + 1));
                }
                res = Math.max(res, curr.idx - first + 1);
            }
        }
        return res;
    }

}
