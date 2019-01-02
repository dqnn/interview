package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RangeAddition
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 370. Range Addition
 */
public class RangeAddition {
    /**
     * Assume you have an array of length n initialized with all 0's and are given k update operations.

     Each operation is represented as a triplet: [startIndex, endIndex, inc]
     which increments each element of subarray A[startIndex ... endIndex] 
     (startIndex and endIndex inclusive) with inc.

     Return the modified array after all k operations were executed.
     * Example:

     Given:

     length = 5,
     updates = [
     [1,  3,  2],
     [2,  4,  3],
     [0,  2, -2]
     ]

     Output:

     [-2, 0, 3, 5, 3]
     Explanation:

     Initial state:
     [ 0, 0, 0, 0, 0 ]

     After applying operation [1, 3, 2]:
     [ 0, 2, 2, 2, 0 ]

     After applying operation [2, 4, 3]:
     [ 0, 2, 5, 5, 3 ]

     After applying operation [0, 2, -2]:
     [-2, 0, 3, 5, 3 ]

     time : O(K + N);
     space : O(n)


     * @param length
     * @param updates
     * @return
     */
    // so each line in updates is an operation
    // 这么想 一排灯，开关可以控制 其中的sub 灯， 最后求 那些灯亮着 还是暗着 所以我们只需要记得首尾就行
    public int[] getModifiedArray(int length, int[][] updates) {
        //initialize as 0
        int[] res = new int[length];
        // we only cared about startIdx (+ value) and endIdx + 1 - value,
        //because for example (1,3,2), for [1,3] elments we add 2 for each, this 2 will 
        //be added at 1, 2 and 3 indexes, in another way, the impact is on 1,2 and 3, so 
        //the impact will disappear on 4th, thats why we need to add -value on endIdx + 1
        
        //another thinking is each op is a gate, start and end, the impact will be within 
        //range, disppear on endIdx + 1, start from startIdx
        for (int[] update : updates) {
            int value = update[2];
            int startIdx = update[0];
            int endIdx = update[1];
            res[startIdx] += value;
            if (endIdx + 1 < length) {
                res[endIdx + 1] -= value;
            }
        }
        // add from begin to end
        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
