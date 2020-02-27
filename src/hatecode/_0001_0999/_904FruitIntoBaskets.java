package hatecode._0001_0999;

import java.util.HashMap;
import java.util.Map;

public class _904FruitIntoBaskets {
/*
 * 904. Fruit Into Baskets
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: 
you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket 
to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 

Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length

 */
    //O(n)= O(N * M) M is constant,  and O(n) or O(2) since map cannot go beyond 2
    
    // thinking process: sliding window, the problem is to find out 
    //i and j in a subarray which they only have 2 distinct numbers, and the length is max
    
    //so we use sliding window to detect whether we have are in boundary or not
    public int totalFruit(int[] tree) {
        if (tree == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        for(int right = 0; right< tree.length; right++) {
            map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
            //check map more than 2
            while (map.size() > 2)  {
                map.put(tree[left], map.get(tree[left]) - 1);
                if (map.get(tree[left]) == 0) {
                    map.remove(tree[left]);
                }
                left++;
            }
            max = Math.max(max, right -left +1);
        }
        return max;
    }
}