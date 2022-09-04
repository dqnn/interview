package hatecode._0001_0999;
import java.util.*;
import java.util.stream.*;
public class _503NextGreaterElementII {
/*
503. Next Greater Element II
Given a circular array (the next element of the last element is the 
first element of the array), print the Next Greater Number for every element. 
The Next Greater Number of a number x is the first greater number to its 
traversing-order next in the array, which means you could search circularly to 
find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
*/
    //thinking process: given an circuit array, last element' next is first element, then 
    //return array which each value is input'a next bigger next value
    
    //so we use TreeMap to save value->list(index) so then we user a tailMap  false to get its 
    //view and assign correct value to the result
     public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length < 1) return new int[0];
        
        TreeMap<Integer, List<Integer>> tree = new TreeMap<>();
        IntStream.range(0, nums.length).forEach(i->tree.computeIfAbsent(nums[i], v->new ArrayList<>()).add(i));
        
        int[] res = new int[nums.length];
        for(int i = 0; i< nums.length; i++) {
            Map<Integer, List<Integer>> sub = tree.tailMap(nums[i], false);
            //System.out.println(nums[i] + "--" + sub);
            res[i] = sub != null ? getIdx(nums, sub, i) : -1;
        }
        return res;
    }
    //to find the next bigger number
    private int getIdx(int[] nums, Map<Integer, List<Integer>> map, int i) {
        int idx = -1, dist = Integer.MAX_VALUE;
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            for(int index : entry.getValue()) {
                int tempDist = index - i;
                if (tempDist < 0) tempDist += nums.length;
            
                if (tempDist < dist) {
                    idx = index;
                    dist = tempDist;
                }
            }
        }
        return idx == -1 ? -1 : nums[idx];
    }
    
    //interview friendly this is so brilliant solution O(n)/O(n)
    /*
     * the problem is to say: given one circuit array A, find next greater element 
     * 
     *  first we use a stack to store all index from n -1 to 0.
     *  
     *  2nd loop, from n-1 to 0, 
     *  
     *  for each index i, we want to try to find next greater element for A[i],
     *  if <= than A[i], pop out until it is empty, but last will put i into stack. 
     *  
     *   0 will be top of the stack, 
     */
    public int[] nextGreaterElements2(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            stack.push(i);
        }
        
        for (int i = n - 1; i >= 0; i--) {
            result[i] = -1;
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()){
                result[i] = A[stack.peek()];
            }
            stack.push(i);
        }

        return result;
    }
}