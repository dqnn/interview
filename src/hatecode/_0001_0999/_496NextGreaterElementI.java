package hatecode._0001_0999;

import java.util.HashMap;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NextGreaterElementI
 * Date : Aug, 2018
 * Description : 496. Next Greater Element I
 */
public class _496NextGreaterElementI {
    /**
     * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset 
     * of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

     The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
     If it does not exist, output -1 for this number.

     Example 1:
     Input: [4,1,2], nums2 = [1,3,4,2].
     Output: [-1,3,-1]
     Explanation:
     For number 4 in the first array, you cannot find the next greater number for it in the second array, 
     so output -1.
     For number 1 in the first array, the next greater number for it in the second array is 3.
     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
     Example 2:
     Input: nums1 = [2,4], nums2 = [1,2,3,4].
     Output: [3,-1]
     Explanation:
     For number 2 in the first array, the next greater number for it in the second array is 3.
     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
     
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

     map : num , res
     stack : num

     [4,1,2], [1,3,4,2]

     1 3 4 2
           i
     stack : 4 2

     map : 1 3
           3 4

     time : O(n)
     space : O(n)

     * @param nums1
     * @param nums2
     * @return
     */
    //thinking process:  O(n+m)/O(m) n = num1.length, m = num2.length
    // the problem is to find the next greater element in num 2for each element in nums1
    //[4,1,2], [1,3,4,2]
    //4 in num2 right is [4, 2], no one is bigger than 4, so return -1.
    //1 in num2 [3,4,2], so return 3
    //2 in num2 is [], so return -1.
    
    //
    
    //the solution is another thinking of how to store the element relationship, increasing sequence
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // the map is used to store the number relationship in nums2, and from left 
        //to right, only two closest number can have such relation if first < second
        // [1,3,4,2]--> map 1->3, 3->4, 
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        //so if it is 5,3,4,6--> stack: 5,3 and num = 3, so map: 3->4, stack:4/5 next 
        //num = 6, map: 3->4, 4->6, 5->6, stack is empty. so 
        //this template aimed to find in one array, the closest greater/smaller number
        //these two are perfect match for element relationship
        for (int num : nums2) {
            // we are using stack to find next great 
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            // adding to stack
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
