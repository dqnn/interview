package hatecode._0001_0999;
import java.util.*;
import java.util.stream.IntStream;
public class _889ConstructBinaryTreeFromPreorderAndPostorderTraversal {
/*
889. Construct Binary Tree from Preorder and Postorder Traversal
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
*/
    //thinking process: 
    //TODO: 1. output all possible results, 2 duplicate,3 understand the map dfs version
    //from pre and post to construct the binary tree
    public static TreeNode constructFromPrePost_Stack(int[] pre, int[] post) {
        if (pre == null || pre.length < 1 || post == null || post.length < 1 || post.length != pre.length) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(pre[0]);
        stack.push(root);
        for(int i = 1, j =0; i< pre.length; i++) {
            TreeNode node = new TreeNode(pre[i]);
            while(stack.peek().val == post[j]) {
                stack.pop();
                j++;
            }
            
            if (stack.peek().left == null) {
                stack.peek().left = node;
            } else stack.peek().right = node;
            
            stack.push(node);
        }
        
        return root;
    }
    
    //use deque as stack，
    
    //same as previous stack solution, post[j] means all node before j all visited
/*
 *   from pre and post visit array, there were multiple answers, for pre= [1,2,3],  post=[3,2,1]
        1          1        1
       / \         /         \ 
      2   3       2            2
                 /              \ 
                3                3
latter 2 are all possible results, 
 so we can only know from post order is that for val post[j], we could know which nodes are
 visited.  
 
         int[] pre= {1,2,4,5,3,6,7};
         int[] post= {4,5,2,6,7,3,1};
         1,2,4 and    1.left = 2, 2.left = 4, 
         then node = 5, enter while, and we pop 4, found 2 left already = 4, so 
         2.right = 5. j move to 2
         
         then stack.peek = 5, node = 3, we 
 */
     public static TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || pre.length < 1 || post == null || post.length < 1 || post.length != pre.length) {
            return null;
        }

        Deque<TreeNode> s = new ArrayDeque<>();
        s.offer(new TreeNode(pre[0]));
         
        for(int i = 1, j = 0; i< pre.length;i++) {
            TreeNode node = new TreeNode(pre[i]);
            //we found they 
            while(s.getLast().val == post[j]) {
                s.pollLast();
                j++;
            }
            
            if (s.getLast().left == null) s.getLast().left = node;
            else s.getLast().right = node;
            
            s.offer(node);
        }
        
        return s.getFirst();
     }
/*
 example
pre : 1(245)(367)
post : (452)(673)1
pre: 2 (4)(5) 3(6)(7)
post:(4)(5)2 (6)(7)3
....so each time the first node of pre array 1 and the 
last node of post array 1 if they are equal means we just 
found a root, then the next node 2 in pre array will be the root 
of the sub tree, then since we use HashMap to store all the nodes' index, 
it will be very easy to find the index of 2 in post array, 
then we cut the post array into half by this index, then do the recursion, 
until we have only one node left which is the base case for the recursion..
 */
     public TreeNode constructFromPrePost_Map(int[] pre, int[] post) {
         //edge case check
         Map<Integer, Integer> map = new HashMap<>();
         IntStream.range(0, post.length - 1).forEach(e->map.put(post[e], e));
         return dfs(pre, 0, pre.length-1, post, 0, post.length-1, map);
     }
     private TreeNode dfs(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd, Map<Integer, Integer> map){
         if (preStart > preEnd || postStart > postEnd){
             return null;
         }
         TreeNode root = new TreeNode(pre[preStart]);
         if (preStart + 1 <= preEnd){
             int deltaIndex = map.get(pre[preStart+1]) - postStart;
             root.left = dfs(pre, preStart + 1, preStart + 1 + deltaIndex, post, postStart, postStart + deltaIndex, map);
             root.right = dfs(pre,  preStart + 1 + deltaIndex + 1, preEnd, post, postStart + deltaIndex + 1, postEnd - 1, map);
         }
         return root;
     }
    
     public static void main(String[] args) {
         int[] pre= {1,2,4,5,3,6,7};
         int[] post= {4,5,2,6,7,3,1};
         System.out.println(constructFromPrePost(pre, post));
     }
}