package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniqueBinarySearchTreesII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class UniqueBinarySearchTreesII {
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

    //interview frinendly:
    
    //thinking process:
    //so the problem is to get list of root node of BST tree which contains 1->n nodes, 
    //so if consider each number i will be root node, then left(0, i -1) and right i+1, n, we can 
    //recursively setup the tree. 
    
    //if we think about top down, we can easily to have recursive for loop to construct the n-child tree
    //for each unit, left child and right child, 
    public List<TreeNode> generateTrees2(int n) {
        if (n == 0) return new ArrayList<>();
        return genTreeList(1, n);
    }

    // Tree structure, the tree has n child tree
    public List<TreeNode> genTreeList(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        //note: this is exit condition, and note we add null into list because left or right child maybe null
        //and we need to assign them to child tree, null left and right is allowable
        if (start > end) {
            list.add(null);
        }
        for (int idx = start; idx <= end; idx++) {
            //we split the range into two parts and we recursive on each part by genTreeList() 
            // note the for loop here is perfect
            List<TreeNode> leftList = genTreeList(start, idx - 1);
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            //for each left child, we add the into list
            //two loops to setup left and right tree with idx as root
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
