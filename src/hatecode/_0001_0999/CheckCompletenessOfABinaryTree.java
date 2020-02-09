package hatecode._0001_0999;
import java.util.*;

public class CheckCompletenessOfABinaryTree {
/*
958. Check Completeness of a Binary Tree
Given a binary tree, determine if it is a complete binary tree.

In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
Input: [1,2,3,4,5,null,7]
Output: false
*/
    //interview friendly and thinking process
    //given complete BT definition, we want to detect whether one 
    //given tree is complete BT, if we take close look on the tree,
    //we can find that if we level scan the tree, there should not be null, if there are then it must be last and only one. 
    //or if not, it is not as left as possible or not previous node must not be filled. 
    public boolean isCompleteTree_BEST(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        //this is to indicate we have found null node same level
        boolean isFound = false;
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                if (!isFound) isFound = true;
                continue;
            } else if (isFound) return false;
            //no matter it is null or not
            q.offer(node.left);
            q.offer(node.right);
        }
        
        return true;
    }
    
    class ANode{
        int code;
        TreeNode node;
        public ANode(int code, TreeNode node) { 
            this.code =code;
            this.node = node;
        }
    }
    // so we leverage complete tree one rule is that the node size of complete BT,
    //if we mark each node as a number, starting from 1, when we add each child nodes into the 
    //queue, 2*i or 2*i +1, then list.size() shoul equals to last one. or if not, they will not be equal.
     public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        List<ANode> list = new ArrayList<>();
         int i = 0;
         list.add(new ANode(1, root));
         while(i < list.size()) {
             ANode a = list.get(i++);
             if (a.node != null) {
                 list.add(new ANode(2* a.code, a.node.left));
                 list.add(new ANode(2* a.code + 1, a.node.right));
             }
         }
         
         return list.get(list.size() - 1).code == list.size();
     }
    
    
}