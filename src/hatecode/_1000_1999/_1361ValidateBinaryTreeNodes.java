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
    //thinking process: O(n)/O(n)
    //the problem is to say: given n nodes,0 - n-1, two array l and r which means
    //
    public boolean validateBinaryTreeNodes(int n, int[] l, int[] r) {
        int[] in = new int[n];
        // check nodes has more than 1 in degree, but we do not check root
        /*
        4
[1,-1,3,-1]
[2,-1,-1,-1]
        */
        for(int i = 0; i< n; i++) {
            //
           if(l[i] != - 1 && in[l[i]]++ == 1) { return false; }
           if(r[i] != - 1 && in[r[i]]++ == 1)  { return false; }
            
        }
        
        //check we have forests
        /*
        6
[1,-1,-1,4,-1,-1]
[2,-1,-1,5,-1,-1]

2
[1,0]
[-1,-1]

        */
        
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                if (root != -1) return false; // Multiple root
                root = i;
            }
        }
        
        
        Set<Integer> visited = new HashSet<>();
        return helper(visited, 0, n, l, r);
    }
    
    private boolean helper(Set<Integer> visited, int pos, int n, int[] l, int[] r) {
        if (pos ==-1 ) return true;
        
        if (visited.contains(pos)) return false;
        visited.add(pos);
        //System.out.println(visited);
        return helper(visited, l[pos], n, l, r) && helper(visited, r[pos], n, l, r);
    }
}