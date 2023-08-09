package hatecode._0001_0999;
import java.util.*;
import java.util.stream.*;
public class _987VerticalOrderTraversalOfABinaryTree {
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


    /*
     * below is TreeMap solution, y as column, x as row 
     * 
     * the critical part is when we sort, 
     */
    class Pair{
        TreeNode node;
        int x, y;
        public Pair(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }
    public List<List<Integer>> verticalTraversal_TreeMap(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, 0));
        map.computeIfAbsent(0, v->new ArrayList<>()).add(new int[]{root.val, 0});
        
        while(!q.isEmpty()) {
            Pair p = q.poll();
            if (p.node.left != null) {
                q.offer(new Pair(p.node.left, p.x+1, p.y-1));
                map.computeIfAbsent(p.y-1, v->new ArrayList<>()).add(new int[]{p.node.left.val, p.x+1});
            }
            if (p.node.right != null) {
                q.offer(new Pair(p.node.right, p.x+1, p.y+1));
                map.computeIfAbsent(p.y+1, v->new ArrayList<>()).add(new int[]{p.node.right.val, p.x+1});
            }
        }
        
        for(List<int[]> list: map.values()) {

            // so the problem is confusing what it is saying:
            /*
             * TreeMap 
             */
            Collections.sort(list, (a, b)->(a[1]== b[1]? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])));
            res.add(list.stream().map(x->x[0]).collect(Collectors.toList()));
        }
        return res;
    }
}