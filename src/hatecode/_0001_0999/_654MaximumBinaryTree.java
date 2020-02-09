package hatecode._0001_0999;
import java.util.*;
public class _654MaximumBinaryTree {
/*
654. Maximum Binary Tree
Given an integer array with no duplicates. A maximum tree building on this array 
is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by 
the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided 
by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:
*/
    //O(n)/O(n)
    //the parent of a node = min(nearest max to the left, nearest max to the right)
/*
If we have built the max binary tree for nums[0] ~ nums[i - 1], how can we insert 
nums[i] to the binary tree?
Say the max binary tree for nums[0] ~ nums[i - 1] looks like:

      A
     / \
  ...   B
       / \
    ...   C
         / \
      ...   ...
Say the node for nums[i] is D.
If D.val > A.val, then because A.val is at the left of D.val, we can just move the 
tree rooted at A to the left child of D.

        D
       /
      A
     / \
  ...   B
       / \
    ...   C
         / \
      ...   ...
If D.val < A.val, then because D.val is at the right of A.val, 
D must be put into the right subtree of A.
Similarly, if D.val < B.val, then D must be put into the right subtree of B.
Say B.val > D.val > C.val, then D should be the right child of B. (because D.val 
is at the right of B.val, and D.val is the biggest among the numbers at the right of B.val.)
Because C.val < D.val, and C.val is at the left of D.val, C should become left child of D.

      A
     / \
  ...   B
       / \
     ...  D
         /
        C // c is on the left of D
       / \
    ...   ...
So to update the max binary tree for nums[0] ~ nums[i - 1], we need to know the 
nodes on the right path of the tree. (A, B, C, ...)
How to maintain the path?
Let's look at the property of the nodes.
A is the biggest among nums[0] ~ nums[i - 1].
B is the biggest for the numbers between A and nums[i] (exclusive).
C is the biggest for the numbers between B and nums[i] (exclusive).
Let's use a stack, and assume that the content of the stack contains the "right path" of 
nodes before the node for the current number.
For the node of the new number, we should remove the nodes in the stack which are smaller 
than the current number.
So we pop the stack until the top element of the stack is greater than the current number.
Then, add the node for the current number to the stack.
 */
    
    //thinking process, 
    //max number is the root, we cut the array into two parts, 
    //left will become left tree, right will become right tree, so for each subtree
    //even for sub tree, if it comes on the left, then it should be on left side, else 
    //on right side, these are the rules
    
    
    //construct a maximum binary tree given an array
    //from recursive we know how we can contruct the tree, so iteratively, we can use a Deque, 
    //the reason why we use deque because we want to return the bottom node of a stack which is the root node
    //scan the array from left to right, 
    //if we found peek() < A[i] then we should replace cur.left = pop() until we encounter a bigger one
    //then we should attach cur to bigger node in stack, peek().right = cur; 
    //every time, we should push node into stack
    
    //last return bottom node of the deque
    public TreeNode constructMaximumBinaryTree(int[] A) {
        Deque<TreeNode> dq = new LinkedList<>();
        for(int i = 0; i < A.length; i++) {
            TreeNode curr = new TreeNode(A[i]);
            while(!dq.isEmpty() && dq.peek().val < A[i]) {
                curr.left = dq.pop();
            }
            if(!dq.isEmpty()) {
                dq.peek().right = curr;
            }
            dq.push(curr);
        }
        
        return dq.isEmpty() ? null : dq.removeLast();
    }
    //[1]--
    //[2,1]--> 
    //O(n^2)/ O(n)--worst case, if sorted, O(nlgn)/O(lgn), note that tree will have lgn depth if sorted
    //if like a linkedlist tree style then it would have n depth, that's why time complexity is O(n^2)
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        return helper(nums, 0, nums.length -1);
    }
    
    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) return null;
        else if (start == end) return new TreeNode(nums[start]);
        int index = start;
        for(int i = start; i<=end; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = helper(nums, start, index-1);
        root.right = helper(nums, index+1, end);
        return root;
    }
}