package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description : TODO
 */
public class _297SerializeandDeserializeBinaryTree {

    /**
     * 297. Serialize and Deserialize Binary Tree
     *
     *
       1
      / \
     2   3
        / \
       4   5
     as "[1,2,3,null,null,4,5]"
     * time : O(n);
     * space : O(n);
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    //thinking process:O(n)/O(n)
    // we want to convert tree to String and decode the string to tree. 
    // we want to use "null" to indicate the node is is null like array to represent 
    // string, so when we converting from tree to string, we definitely there are null in string
    // and when we decode the string, there cannot be null node at all. 
    
    // BFS on tree, we use Queue to store the node and it can be null to be added into queue
    // we can use space or "," to split string please note when decode we has no case on node from queue
    // is null because we never add null into queue because we node is initialized as null. 
    //
    public String serialize(TreeNode root) {
       if (root == null) return "";
       StringBuilder res = new StringBuilder();
       Queue<TreeNode> queue = new LinkedList<>();
       queue.offer(root);
       while (!queue.isEmpty()) {
           TreeNode cur = queue.poll();
           if (cur == null) {
               res.append("null ");
               continue;
           }
           res.append(cur.val + " ");
           queue.offer(cur.left);
           queue.offer(cur.right);
       }
       return res.toString();
    }

    // Decodes your encoded data to tree.
    //note we use a queue to go along with a for loop, the way how we preserve the 
    //tree is straightforward
    ////O(n)/O(n)
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] d = data.split(" ");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root;
        
        if(d[0].equals("null")) return null;
        root = new TreeNode(Integer.valueOf(d[0]));
        q.offer(root);
        
        for(int i = 1; i< d.length; i++) {
            TreeNode cur = q.poll();
            if(!d[i].equals("null")) {
                cur.left = new TreeNode(Integer.valueOf(d[i]));
                q.offer(cur.left);
            }
            if (!d[++i].equals("null")) {
                cur.right = new TreeNode(Integer.valueOf(d[i]));
                q.offer(cur.right);
            }
        }
        return root;
    }
}
