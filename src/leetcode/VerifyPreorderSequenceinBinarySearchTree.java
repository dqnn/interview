package leetcode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : VerifyPreorderSequenceinBinarySearchTree
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 255. Verify Preorder Sequence in Binary Search Tree
 */
public class VerifyPreorderSequenceinBinarySearchTree {
    /**
     *    6
         / \
        1  8
      /  \
     0   3
        / \
       2  5

     preorder : 6 1 0 3 2 5 8

     num : 2             5   8
     min : 1             3    6
     stack : 6，  5          8

     time : O(n)
     space : O(n)

     * @param preorder
     * @return
     */
    // thinking process:
    // so the problem is asking a way to verify the poreorder sequence in an array is correct or not
    // mostly we have rules on BST, so we want to check BST tree rules, 
    //the arrays is using Broad visit the tree
    
    // so we construct a stack which will push elements in array into the stack, so what this stack
    //is to store all elements until we met number in array which is smaller than previous pop one. 
    
    
    //same as Skyline problem, with a queue and visiting the array
    public static boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length < 1) return true;
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num < min) {
                return false;
            }
            //so at this time, (pre-visit)peek always means parent node(left child is null) or 
            //left child node,
            //so this is to verify that its right element should be bigger than parent or left child
            while (!stack.isEmpty() && num > stack.peek()) {
                min = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}
