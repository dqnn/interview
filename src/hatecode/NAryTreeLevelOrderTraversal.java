package hatecode;

import java.util.*;
public class NAryTreeLevelOrderTraversal {
/*
429. N-ary Tree Level Order Traversal
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]
*/
    //level traverse
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            while(size-- > 0) {
                Node node = q.poll();
                if (node == null) continue;
                for(Node e : node.children) {
                    if (e != null) q.offer(e);
                }
                list.add(node.val);
            }
            res.add(list);
        }
        return res;
    }
}