package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BinaryTreeVerticalOrderTraversal
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 314. Binary Tree Vertical Order Traversal
 */
public class BinaryTreeVerticalOrderTraversal {
    /**
     *Given a binary tree, return the vertical order traversal of 
     *its nodes' values. (ie, from top to bottom, column by column).

     If two nodes are in the same row and column, the order should be from 
     left to right.

     Examples:

     Given binary tree [3,9,20,null,null,15,7],
         3
        / \
       9  20
          / \
        15  7

              |
      . _ * _ * _ 0 _ . _ *
     -2  -1   0   1   2   3

     return its vertical order traversal as:
     [
     [9],
     [3,15],
     [20],
     [7]
     ]
     Given binary tree [3,9,8,4,0,1,7],
          3
         / \
       9    8
      / \  / \
     4  0 1   7
     -2  0   2

     return its vertical order traversal as:
     [
     [4],
     [9],
     [3,0,1],
     [8],
     [7]
     ]

     1, dfs max min
     2, bfs

     time : O(n)
     space : O(n)


     */
    private int min = 0;
    private int max = 0;
    //thinking process:  the problem is to output the List which contains list which is 
    //scan vertically, notes, there are some elements overlapped but they should be in order
    //so last requirement is key that we have to add the elements when we access it, 
    //if we +1 or -1, the elment will lose the info of accessing order. 
    //so we use map: idx-> List<Integer> to record each vertical List,idx is verrical idx
    //
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, 0);
        for (int i = min; i <= max; i++) {
            res.add(new ArrayList<>());
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        index.offer(-min);// since it highly negative and MAX{min} = 0 and list index starts from 0; which
                          // means root index = - minus
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int idx = index.poll();
            res.get(idx).add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                index.offer(idx - 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                index.offer(idx + 1);
            }
        }
        return res;
    }

    private void helper(TreeNode root, int idx) {
        if (root == null) return;
        min = Math.min(min, idx);
        max = Math.max(max, idx);
        helper(root.left, idx - 1);
        helper(root.right, idx + 1);
    }
    
    
  //interview friendly: 
    //this solution is iterative, not recursive 
    
    //
    class Node2{
        int idx;
        TreeNode node;
        public Node2(int idx,TreeNode node){
            this.idx = idx;
            this.node = node;
        }
    }
    public List<List<Integer>> verticalOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Node2>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Queue<Node2> q = new LinkedList<>();
        q.offer(new Node2(0,root));
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i< size;i++) {
                Node2 node = q.poll();
                map.computeIfAbsent(node.idx, v->new ArrayList<>()).add(node);
                min = Math.min(min, node.idx);
                max = Math.max(max, node.idx);
                if (node.node.left != null){
                    q.offer(new Node2(node.idx - 1, node.node.left));
                }
                if (node.node.right != null){
                    q.offer(new Node2(node.idx + 1, node.node.right));
                }
            }
        }
        
        for(int i =min; i<=max;i++) {
             List<Integer> temp = new ArrayList<>();
            for(Node2 node : map.get(i)) {
                temp.add(node.node.val);
            }
            res.add(temp);
                
        }
        return res;
    }
    
    
    
    
    
    class Node {
        int level;
        int val;
        int idx;
        //left is 1, right is 0, compare to its parent, -1 is root
        int pos;
        public Node(int level, int val, int idx, int pos) {
            this.level = level;
            this.val = val;
            this.idx = idx;
            this.pos = pos;
        }
        public String toString() {
            return String.format("%s:%s:%s:%s", idx, level, pos, val);
        }
    }
    //this solution has 1 use case cannot pass, the reason is 
    //when we visit the tree recursively. if two nodes are same 
    //level, same idx, so they must be ordered by access order, like pre order
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //idx, level and left, right position sort
        PriorityQueue<Node> pq = 
            new PriorityQueue<>(
            (a,b)->(a.idx == b.idx ? 
                    (a.level == b.level ? a.pos - b.pos : a.level - b.level)
                    : a.idx-b.idx));
        helper(root, pq, 0, 0, 0);
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            System.out.println(node);
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            int preIdx = node.idx;
            while(!pq.isEmpty() && pq.peek().idx == preIdx) {
                System.out.println(pq.peek());
                list.add(pq.poll().val);
            }
            //this is just for [1,2,3,null,5,6] case to pass leetcode submit
            //which [[2],[1,6,5],[3]] vs [[2],[1,5,6],[3]]
            //1.6.5 should be ok
            //Collections.sort(list);
            res.add(list);
        }
        return res;
    }
    
    private void helper(TreeNode node, PriorityQueue<Node> pq, int level, int idx, int pos) {
        if (node == null) return;
        pq.offer(new Node(level, node.val, idx, pos));
        if (node.left != null) {
            helper(node.left, pq, level + 1, idx - 1, pos + 1);
        }
        if (node.right != null) {
            helper(node.right, pq, level + 1, idx + 1,pos- 1);
        }
    }
    
}
