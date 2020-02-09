package hatecode._0001_0999;
import java.util.*;
public class NumberOfLongestIncreasingSubsequence {
/*
 * 673. Number of Longest Increasing Subsequence
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, 
and there are 5 subsequences' length is 1, so output 5.
 */
        //O(n^2)/O(n)
    //Dp solution, 
    public static int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) return N;
        int[] lengths = new int[N]; // lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[N]; // count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);
        //[1,3,5,4,7]--> i = 0, j =0, then nothing happen, so 
        //i = 1, j =0,  then true, length[i] = length[0] + 1 = 1, count[1] = count[0]
        // i =2, j =0,  true, length[2] = 0, length[0] = 0, so we update length[2] = 0 + 1,
        //count[2] = count[0] = 1
        //i = 2, j= 1, true. length[1] = length[2],so length[2] = length[1] + 1  =2, count is the
        //same,
        //basica idea, [1,3] =LIS(3), LIS(5) = max(LIS(3) +1, LIS(1) +1..)
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < i; ++j) 
                if (nums[j] < nums[i]) {
                    if (lengths[j] >= lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    //this means we found another number, which is smaller than nums[j]
                        //but it is another sequence, since number is different
                        //like ,1,3,5,7, 1,4,5,7, because previously it already 
                        //1 number is in the queue
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        //len = [0, 1, 2, 2, 3], counts= [1, 1, 1, 1, 2]
        int longest = 0, res = 0;
        for (int length : lengths) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < N; ++i) {
            if (lengths[i] == longest) {
                res += counts[i];
            }
        }
        return res;
    }
    //hashMap solutions
    public int findNumberOfLIS2(int[] nums) {
        List<Map<Integer, Integer>> maps = new ArrayList<>(nums.length);
        int maxLength = 1;
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            maps.add(i, map);
            map.put(1, 1);
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    for (Map.Entry<Integer, Integer> e : maps.get(j).entrySet()) {
                        int l = e.getKey() + 1;
                        maxLength = Math.max(maxLength, l);
                        map.compute(l, (k, v) -> ((v == null) ? 0 : v) + e.getValue());
                    }
                }
            }
        }
        int count = 0;
        for (Map<Integer, Integer> map : maps) {
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                if (e.getKey() == maxLength) {
                    count += e.getValue();
                }
            }
        }
        return count;
    }
    
    //Segement tree solution, O(nlgn)/O(n), 
    //introduction to segment tree: 
    //https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
    class Solution2 {
    public Value merge(Value v1, Value v2) {
        if (v1.length == v2.length) {
            if (v1.length == 0) return new Value(0, 1);
            return new Value(v1.length, v1.count + v2.count);
        }
        return v1.length > v2.length ? v1 : v2;
    }

    public void insert(Node node, int key, Value val) {
        if (node.range_left == node.range_right) {
            node.val = merge(val, node.val);
            return;
        } else if (key <= node.getRangeMid()) {
            insert(node.getLeft(), key, val);
        } else {
            insert(node.getRight(), key, val);
        }
        node.val = merge(node.getLeft().val, node.getRight().val);
    }

    public Value query(Node node, int key) {
        if (node.range_right <= key) return node.val;
        else if (node.range_left > key) return new Value(0, 1);
        else return merge(query(node.getLeft(), key), query(node.getRight(), key));
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int min = nums[0], max = nums[0];
        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        Node root = new Node(min, max);
        for (int num: nums) {
            Value v = query(root, num-1);
            insert(root, num, new Value(v.length + 1, v.count));
        }
        return root.val.count;
    }
}

class Node {
    int range_left, range_right;
    Node left, right;
    Value val;
    public Node(int start, int end) {
        range_left = start;
        range_right = end;
        left = null;
        right = null;
        val = new Value(0, 1);
    }
    public int getRangeMid() {
        return range_left + (range_right - range_left) / 2;
    }
    public Node getLeft() {
        if (left == null) left = new Node(range_left, getRangeMid());
        return left;
    }
    public Node getRight() {
        if (right == null) right = new Node(getRangeMid() + 1, range_right);
        return right;
    }
}

class Value {
    int length;
    int count;
    public Value(int len, int ct) {
        length = len;
        count = ct;
    }
}

public static void main(String[] args) {
    int[] in = {1,3,5,4,7};
    System.out.println(findNumberOfLIS(in));
}
}