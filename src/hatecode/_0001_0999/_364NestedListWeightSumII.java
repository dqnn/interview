package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NestedListWeightSumII
 * Date : Aug, 2018
 * Description : 364. Nested List Weight Sum II
 */
public class _364NestedListWeightSumII {
    /**
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

     Example 1:
     Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

     Example 2:
     Given the list [1,[4,[6]]], return 17.
     (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

     DFS 2 + DFS(2, Nest)
       DFS 2 +

     nextList : 1, 1, 1, 1

     res = 2 + 2 + 4
        dfs(next, 2) res = 2 + 4

     time : O(n);
     space : O(n);
     * @param nestedList
     * @return
     */
    
    //O(nm)/O(m)
    
    //the key of the problem is to understand how to pass the current level 
    //value to next level without knowing the exact current level value,
    //we use a trick here that when going to next level, we use sum in current level, 
    //in next level, we add sum again. note: sum + sum + sum != sum x2 x 2...
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        return helper(nestedList, 0);
    }

    private int helper(List<NestedInteger> nestedList, int res) {
        List<NestedInteger> nextList = new LinkedList<>();
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                res += nest.getInteger();
            } else {
                nextList.addAll(nest.getList());
            }
        }
        res += nextList.isEmpty() ? 0 : helper(nestedList, res);
        return res;
    }

    // interview friendly,
    //thinking process:
    //the problem is reversly sum, the leaf will be last level, and the root will 
    //have the highest level, we can also try top-down accumulate the level, but it sounds 
    //will have 2nd visit, first to make sure the each node level, seond is sum them up. 
    
    //now we think differently, each time we visit lower level, its top will be add another time
    //this is the key, so we pass res to lower level then add them 
    
    //[[1,2],3,[1,2]], 
    //first sum = 3, then we pass 3 to next level, 1+2+1+2, at last we will add 3 to this level
    //as its sum. 
    public int depthSumInverse2(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        //sum represents its upper level sum, will be passed to next level
        int sum = 0;
        int res = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextList = new LinkedList<>();
            // we broadly scan the the tree and add them as sum，
            // here we visit by outside nestedList
            for (NestedInteger nest : nestedList) {
                if (nest.isInteger()) {
                    sum += nest.getInteger();
                } else {
                    nextList.addAll(nest.getList());
                }
            }
            res += sum;
            // we change the scope, use nextList as nestedList as next level until we meet leaf
            nestedList = nextList;
        }
        return res;
    }
}
