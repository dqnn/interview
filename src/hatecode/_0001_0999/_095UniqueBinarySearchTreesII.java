package hatecode._0001_0999;

import java.util.*;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniqueBinarySearchTreesII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class _095UniqueBinarySearchTreesII {
    /**
     * 95. Unique Binary Search Trees II
     * Given an integer n, generate all structurally unique BST's (binary search trees) 
     * that store values 1...n.

     For example,
     Given n = 3, your program should return all 5 unique BST's shown below.
     Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]

     1         3     3      2      1
      \       /     /      / \      \
       3     2     1      1   3      2
      /     /       \                 \
     2     1         2                 3

     time : O(n^2);
     space : O(n);

     * @param n
     * @return
     */
/*
result[i] stores the result until length i. For the result for length i+1, 
select the root node j from 0 to i, combine the result from left side and right side. 
Note for the right side we have to clone the nodes as the value will be offsetted by j.
 */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] res = new List[n + 1];
        res[0] = new ArrayList<>();
        if (n == 0) return res[0];
        res[0].add(null);
        for (int i = 1; i <= n; i++) {
            res[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (TreeNode left : res[j]) {
                    for (TreeNode right : res[i - j - 1]) {
                        TreeNode root = new TreeNode(j + 1);
                        root.left = left;
                        root.right = clone(right, j + 1);
                        res[i].add(root);
                    }
                }
            }
        }
        return res[n];
    }

    public TreeNode clone(TreeNode root, int k) {
        if (root == null) return root;
        TreeNode cur = new TreeNode(root.val + k);
        cur.left = clone(root.left, k);
        cur.right = clone(root.right, k);
        return cur;
    }
    
    //standard DP to solve the problem the only difference is that this one does 
    //not clone as necessary
        public List<TreeNode> generateTrees_DP(int n) {
            if(n == 0) return new ArrayList<TreeNode>();
            List<List<TreeNode>>[] dp = new List[n + 1];
            dp[0] = new ArrayList<List<TreeNode>>();
            for(int j = 1; j <= n + 1; j++){
                List<TreeNode> temp = new ArrayList<>();
                temp.add(null);
                dp[0].add(temp);
            }        
            dp[1] = new ArrayList<List<TreeNode>>();
            for(int j = 1; j <= n; j++){
                List<TreeNode> temp = new ArrayList<>();
                TreeNode tempNode = new TreeNode(j);
                temp.add(tempNode);
                dp[1].add(temp);
            }        
            for(int i = 2; i < dp.length; i++){
                dp[i] = new ArrayList<List<TreeNode>>();
                generateTreesHelper(dp, i, n);
            }
            return dp[n].get(0);
        }
        
        public void generateTreesHelper(List<List<TreeNode>>[] dp, int n, int total){
            for(int i = 1; i <= total - n + 1; i++){ // for example, n = 4. we need to generate 1234, 2345, 3456
                List<TreeNode> l = new ArrayList<>();
                for(int j = i; j < n + i; j++){
                    List<TreeNode> left = dp[j - i].get(i - 1);
                    List<TreeNode> right = dp[n + i - j - 1].get(j);
                    for(TreeNode leftNode : left){
                        for(TreeNode rightNode : right){
                            TreeNode root = new TreeNode(j);
                            root.left = leftNode;
                            root.right = rightNode;
                            l.add(root);
                        }
                    }   
                }
                dp[n].add(l);
            }
        }

    //interview frinendly:
    
    //thinking process:
    //so the problem is to get list of root node of BST tree which contains 1->n nodes, 
    //so if consider each number i will be root node, then left(0, i -1) and right i+1, n, we can 
    //recursively setup the tree. 
    
    //if we think about top down, we can easily to have recursive for loop to construct the n-child tree
    //for each unit, left child and right child, 
    //O(nG(n))/O(nG(n)), so G(n) = 4^n/n^(1/2)
    public List<TreeNode> generateTrees2(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    // Tree structure, the tree has n child tree, 
    //need to think about how we construct the tree 
    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        //note: this is exit condition, and note we add null into list because left or right child maybe null
        //and we need to assign them to child tree, null left and right is allowable
        /*
         * this is also one way to write, the reason is the for loop even both rlist and lList are null, they 
         * still can mark idx as root, its left and right are null
          if (st > ed) {
            tmpList.add(null);
        } else if (st == ed) {
            tmpList.add(new TreeNode(st));
        } else {
            for (int i=st; i<=ed; i++) {
            }
        }
         */
        if (start > end) {
            list.add(null);
        }
        for (int idx = start; idx <= end; idx++) {
            //we split the range into two parts and we recursive on each part by genTreeList() 
            // note the for loop here is perfect
            List<TreeNode> leftList = helper(start, idx - 1);
            List<TreeNode> rightList = helper(idx + 1, end);
            //for each left child, we add the into list
            //two loops to setup left and right tree with idx as root
            for (TreeNode l : leftList) {
                for (TreeNode r : rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }
    
    /*
     * from google interview doc,  not from LC
     * 
     * generate a random tree, value from 1->N
     * 
     */
    class TreeNodeV2 {
        int            val;
        List<TreeNodeV2> children;

        public TreeNodeV2(int x) {
            val = x;
            children = new ArrayList<>();
        }
    }
    //O(n^2)
    public TreeNodeV2 getTree(int N) {
        if (N == 0)
            return null;
        List<TreeNodeV2> pool = new ArrayList<>();
        for (int i = 0; i < N; i++) pool.add(new TreeNodeV2(i));

        List<TreeNodeV2> tree = new ArrayList<>();
        Random rand = new Random();
        while (pool.size() > 0) {
            int idx = rand.nextInt(pool.size());
            TreeNodeV2 curr = pool.get(idx);
            if (tree.size() == 0) {
                tree.add(curr);
                pool.remove(idx);
            } else {
                int parent = rand.nextInt(tree.size());
                tree.get(parent).children.add(curr);
                tree.add(curr);
                pool.remove(idx);
            }
        }
        return tree.get(0);
    }
    //follow up: 构造一棵无根的label tree
    public Map<Integer, List<Integer>> getRandomTree(int n) {
        if (n <= 2) {
            Map<Integer, List<Integer>> tree = new HashMap<>();
            for (int i = 0; i < n; i++) {
                tree.put(i, new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        tree.get(i).add(j);
                        tree.get(j).add(i);
                    }
                }
            }
            return tree;
        }
        int[] prufer = new int[n - 2];
        Random rand = new Random();
        for (int i = 0; i < prufer.length; i++) {
            prufer[i] = rand.nextInt(n);
        }
        return getTree(prufer);
    }

    public Map<Integer, List<Integer>> getTree(int[] prufer) {
        int n = prufer.length + 2;
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            tree.put(i, new ArrayList<>());
        }
        int[] vertices = new int[n];
        for (int x : prufer) {
            vertices[x]++;
        }
        for (int i = 0; i < prufer.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (vertices[j] == 0) {
                    // connect j <--> prufer[i]
                    tree.get(j).add(prufer[i]);
                    tree.get(prufer[i]).add(j);
                    vertices[j] = -1; // delete from vertex set
                    vertices[prufer[i]]--; // delete from prufer set
                    break;
                }
            }
        }
        // connect the last two vertex with vertices[i] == 0
        Integer first = null;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == 0) {
                if (first == null)
                    first = i;
                else {
                    tree.get(first).add(i);
                    tree.get(i).add(first);
                    break;
                }
            }
        }
        return tree;
    }

}
