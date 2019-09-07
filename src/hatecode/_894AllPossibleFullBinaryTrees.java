package hatecode;
import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _894AllPossibleFullBinaryTrees {
/*
894. All Possible Full Binary Trees
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

*/
    

    //O(2^N)/O(2^N), 
//   thinking process, since each node will only have 0 or 2 nodes
    //then N must be odd number if not, like n =2 it will be invalid
    // so we use map to mem n-> List
        //1. if N = 3 , the number of nodes combination are as follows
        //      left    root    right
        //       1       1        1 
        //--------------N = 3, res = 1----------
        
        //2. if N = 5 , the number of nodes combination, 2 cases, 
        //      left    root    right
        //       1       1        3 (recursion)
        //       3       1        1 
        //  --------------N = 5, res = 1 + 1 = 2----------
        
        //3. if N = 7 , the number of nodes combination, 5 cases
        //      left    root    right
        //       1       1        5 (recursion)
        //       3       1        3 
        //       5       1        1
        //       2       1        4
        //       4       1        2
        //  --------------N = 7, res = 2 + 1 + 2 = 5----------
        
        //4. in order to make full binary tree, the node number must increase by 2
    //how to figure out the recursive function
    //so  for simplest  tree N >= 3, 
/*
       0
      / \
     0   0
so for left nodes, N - 3, we can put it left or right, this can be applied for each sub tree
 we use two loops 
 for left from 1 to N-1
     for F(left)
        for F(N - left - 1)
 for each recursive, we put N->List() into the map
 */
    Map<Integer, List<TreeNode>> visited = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N == 0 || N % 2 == 0) return res;
        //we put the N->List inside the if closure
        if (!visited.containsKey(N)) {
            if (N == 1) res.add(new TreeNode(0));
            //N >= 3 case, top-down solution
            else if (N % 2 == 1) {
                //we loop from 1->N-1, 0 can be ignored because we have no left l = 0
                //why N - 1 because in inner loop, we want to create a new Node every time,
                //so left means left tree, right is right tree
                for (int l = 1; l < N; l += 2) {
                    int r = N - 1 - l;
                    //left is l
                    for (TreeNode left: allPossibleFBT(l))
                        //right has N- 1 - l nodes
                        for (TreeNode right: allPossibleFBT(r)) {
                            TreeNode bns = new TreeNode(0);
                            bns.left = left;
                            bns.right = right;
                            res.add(bns);
                        }
                }
            }
            visited.put(N, res);
        }

        return visited.get(N);
    }
}