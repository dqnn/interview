package hatecode._1000_1999;

import java.util.*;
public class _1157OnlineMajorityElementInSubarray {
/*
 * 1157. Online Majority Element In Subarray
Implementing the class MajorityChecker, which has the following API:

MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
int query(int left, int right, int threshold) has arguments such that:
0 <= left <= right < arr.length representing a subarray of arr;
2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times, or -1 if no such element exists.

 

Example:

MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
majorityChecker.query(0,5,4); // returns 1
majorityChecker.query(0,3,3); // returns -1
majorityChecker.query(2,3,2); // returns 2
 

Constraints:

1 <= arr.length <= 20000
1 <= arr[i] <= 20000
For each query, 0 <= left <= right < len(arr)
For each query, 2 * threshold > right - left + 1
The number of queries is at most 10000
*/

    //thinking process: O(lgn)/O(1)
    
    //the problems is to say: given a integer array, implement API query(l, r, threshold),
    //you need to find out the majority elements during [l ,r] as subarray in given array
    
    //this is the binary search solution. here is one trick, 
    
    //TODO: why binary seach can get majority elements?
    //if change l + 1 <r, how to write, 
    //the difference between current template with Collections.binarySearch() return value. 
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] A;
    public _1157OnlineMajorityElementInSubarray(int[] arr) {
        A = arr;
        for(int i = 0; i < A.length; i++) {
            map.computeIfAbsent(A[i], v->new ArrayList<>()).add(i);
        }
    }
    
    private int find_low(List<Integer> list, int t) {
        int l = 0, r = list.size();
        while(l < r) {
            int m = (r - l) /2 + l;
            int idx = list.get(m);
            if(idx >= t) r = m;
            else l = m + 1;
        }
        return l;
    }
    
    private int find_high(List<Integer> list, int t) {
        int l = 0, r = list.size();
        while(l < r) {
            int m = (r - l) /2 + l;
            int idx = list.get(m);
            if(idx <= t) l = m + 1;
            else r = m;
        }
        return l-1;
    }
    
    public int query(int left, int right, int threshold) {
        Random rand_gen = new Random();
/*
 * Here I random pick 20 times,
because there are at most 10000 queries and 2^20 = 1048576 is much bigger than 10000.

If there is a majority element,
I only have 1/(2^20) possibility to miss it.
 */
          for(int i=0;i<20;i++){
              int rand_index = rand_gen.nextInt(right-left+1) + left;
              int num = this.A[rand_index];
              
              int low_index = find_low(map.get(num), left);
              int high_index = find_high(map.get(num), right);
              
              if(high_index-low_index+1>=threshold){
                  //System.out.println(high_index + " " + low_index);
                  return num;
              }
          }
        
          return -1;
    }
}

//thing process: O(n)/O(n)
class _1157OnlineMajorityElementInSubarray_SegmentTree {
//////////////////////////////////////////////
// following code is SegmentTree related stuff
    private SegmentTreeNode buildTree(int start, int end, int[] nums) {
        if (start == end) {
            return new SegmentTreeNode(start, end, new Pair(nums[start], 1));
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end, null);

        int mid = start + (end - start) / 2;
        root.left = buildTree(start, mid, nums);
        root.right = buildTree(mid + 1, end, nums);
        root.pair = mergePair(root.left.pair, root.right.pair);

        return root;
    }

    private Pair queryTree(SegmentTreeNode root, int start, int end) {
        if (start <= root.start && root.end <= end) {
            return root.pair;
        }

        Pair res = new Pair(0, 0);
        int mid = root.start + (root.end - root.start) / 2;
        if (start <= mid) {
            res = mergePair(res, queryTree(root.left, start, end));
        }
        if (mid < end) {
            res = mergePair(res, queryTree(root.right, start, end));
        }

        return res;
    }

    private Pair mergePair(Pair pair1, Pair pair2) {
        if (pair1.val == pair2.val) {
            return new Pair(pair1.val, pair1.freq + pair2.freq);
        }

        if (pair1.freq > pair2.freq) {
            return new Pair(pair1.val, pair1.freq - pair2.freq);
        }

        return new Pair(pair2.val, pair2.freq - pair1.freq);
    }

    static class SegmentTreeNode {
        int start;
        int end;
        Pair pair;
        SegmentTreeNode left;
        SegmentTreeNode right;

        SegmentTreeNode(int start, int end, Pair pair) {
            this.start = start;
            this.end = end;
            this.pair = pair;
            this.left = null;
            this.right = null;
        }
    }

    static class Pair {
        int val;
        int freq;

        Pair(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
}

//voting solution, just for reference
//thinking process: O(sqrtN * logN)/O(1)
class MajorityChecker_Voting {

    int[] data;
    int[] big;
    int bCount;
    int[][] fre;
    int shard;

    public MajorityChecker_Voting(int[] arr) {
        data = arr;
        int[] temp = new int[arr.length];
        int[] totalFre = new int[20001];
        shard = (int) Math.sqrt(arr.length);
        for (int num : arr) {
            totalFre[num]++;
        }
        bCount = 0;
        // 这里存数字，而非频率
        for (int i = 0; i < totalFre.length; i++) {
            if (totalFre[i] >= shard) {
                temp[bCount++] = i;
            }
        }
        big = Arrays.copyOf(temp, bCount);
        fre = new int[bCount][arr.length];
        // 为这些挑选出来的数，进行dp数组计算
        // fre[i][j] big[i]的数，在0～j的频率
        for (int i = 0; i < bCount; i++) {
            if (big[i] == arr[0]) {
                fre[i][0] = 1;
                break;
            }
        }
        for (int i = 0; i < bCount; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (big[i] == arr[j]) {
                    fre[i][j] = fre[i][j - 1] + 1;
                } else {
                    fre[i][j] = fre[i][j - 1];
                }
            }
        }
    }

    public int query(int left, int right, int threshold) {
        // 短的，直接用moore's voting
        if (right - left + 1 < 2 * shard) {
            int cur = -1, count = 0;
            for (int i = left; i < right + 1; i++) {
                if (count == 0) {
                    cur = data[i];
                }
                if (cur == data[i]) {
                    count++;
                } else {
                    count--;
                }
            }
            count = 0;
            for (int i = left; i < right + 1; i++) {
                if (data[i] == cur)
                    count++;
            }
            if (count >= threshold)
                return cur;
            else
                return -1;
        } else {
            // 检查高频数
            for (int i = 0; i < bCount; i++) {
                if (left == 0) {
                    if (fre[i][right] >= threshold) {
                        return big[i];
                    }
                } else {
                    if (fre[i][right] - fre[i][left - 1] >= threshold) {
                        return big[i];
                    }
                }
            }
        }
        return -1;
    }
}
