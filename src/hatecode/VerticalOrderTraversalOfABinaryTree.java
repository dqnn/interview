package hatecode;
import java.util.*;
import java.util.stream.*;
public class VerticalOrderTraversalOfABinaryTree {
/*
987. Vertical Order Traversal of a Binary Tree
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

Example 1:
Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
*/
    
    class Node2{
        int y;
        int val;
        public Node2(int y, int val){
            this.y = y;
            this.val = val;
        }
    }
    //found the lambda cannot work well when we added to TreeMap, so we sort after all things done
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeMap<Integer, List<Node2>> map = new TreeMap<>();
        helper(map, root, 0, 0);
        
        for(int key : map.keySet()) {
            List<Node2> list = map.get(key);
            Collections.sort(list, (a, b)->(a.y == b.y ? a.val-b.val:b.y- a.y));
            
            res.add(list.stream().map(e->e.val).collect(Collectors.toList()));
        }
        return res;
    }
    
    public void helper(TreeMap<Integer, List<Node2>> map, TreeNode node, int i, int j) {
        if (node == null) return;
        System.out.println(String.format("val:%s, i:%s, j:%s", node.val, i, j));
        
        map.computeIfAbsent(i, v->new ArrayList<>()).add(new Node2(j, node.val));
        for(Node2 k : map.get(i)) {
             System.out.print(k.val +"->");
        }
        System.out.println("<-");
        helper(map, node.left, i-1, j-1);
        helper(map, node.right, i+1, j-1);
    }
}