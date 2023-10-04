package hatecode._1000_1999;

import java.util.*;
public class _1361ValidateBinaryTreeNodes {
/*
1361. Validate Binary Tree Nodes

You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem


           0
          / \
        1    2
         \  /
          3
*/
    //thinking process: O(n)/O(n), interview friendly
    //the problem is to say: given n nodes,0 - n-1, two array l and r which means
    //l[i] r[i] means its left and right child
    /*
     *  you need to validate the tree and return true or false
     * 
     * this is directed graph, so we should think about indegree
     * 
     *  there are 3 key points in the problem:
     * 1. in binary tree, every node should have indegree = 1 except root, root is 0
     * 2. there should be only 1 binary tree, no forest there
     * 3. no circle in the binary tree
     * 
     * 
     * we have 3 steps to validate
     * 1. indgree all smaller than 1
     * 2. find root, root should not be -1 
     * 3, start from root, count how many nodes in the tree, so there is no forest 
     * 
     * 
     * step 1 and 2 will make sure there is no circle, also step can partially help if there are multiple forests 
     * step 3 will make sure there is no forest
     * 
     * some edge cases:
     * a.   1 <----> 0 
     *   here in =[1, 1], every node will have 1, pass check 1, will fail in check 2, root will be -1
     * 
     * b.  
     *      1           0
     *     /             \
     *    2               3
     *  it will pass 1 since 2 trees, each one is legit binary tree
     * 
     *  c.
     * 1<--->0,    2--->3
     * 
     * it will pass 1 and 2 since it will only have 1 root (2), but it will be caught in step 3
     * 
     * so we have to have 3 steps
     */
    
    public boolean validateBinaryTreeNodes(int n, int[] l, int[] r) {
        if(n <=1) return true;
        
        int[] in = new int[n];
        for(int i = 0;i<n; i++) {
            if(l[i] != -1) in[l[i]]++;
            if(r[i] != -1) in[r[i]]++;
        }
        
        for(int i = 0; i<n; i++){
            if(in[i] > 1) return false;
        }
        
        int root = -1;
        for(int i = 0; i<n; i++) {
            if(in[i] == 0) {
                if(root == -1) {
                    root = i;
                } else return false;
            }
        }
        
        
        if(root == -1) return false;
        
        Set<Integer> visited = new HashSet<>();
        visited.add(root);
        helper(root, l, r, visited);
        
        return visited.size() == n;
    }
    
    private void helper(int s, int[] l, int[] r, Set<Integer> visited) {
        if(s == -1) return;
        int next = l[s];
        if (next != -1 && !visited.contains(next)) {
            visited.add(next);
            helper(next,l, r, visited);
        }
        next = r[s];
        if (next != -1 && !visited.contains(next)) {
            visited.add(next);
            helper(next,l, r, visited);
        }
    }