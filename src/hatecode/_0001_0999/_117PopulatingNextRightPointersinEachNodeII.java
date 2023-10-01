package hatecode._0001_0999;

/**
 * File Name : PopulatingNextRightPointersinEachNodeII
 * Date : Oct, 2017
 * 116. Populating Next Right Pointers in Each Node
 * Description : 117. Populating Next Right Pointers in Each Node II
 */
public class _117PopulatingNextRightPointersinEachNodeII {
    /**
     * For example,
     Given the following binary tree,
          1
        /  \
       2    3
      / \    \
     4   5    7
     After calling your function, the tree should look like:
          1 -> NULL
        /  \
       2 -> 3 -> NULL
      / \    \
     4-> 5 -> 7 -> NULL

     time : O(n);
     space : O(1);

     * @param root
     */
    //thinking process: compare to I, 
    //I is perfect tree,
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = null;
        TreeLinkNode pre = null;
        TreeLinkNode cur = null;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else head = cur.left;
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else head = cur.right;
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            pre = null;
            head = null;
        }
    }
    /*
     * interview friendly O (n)/O(1)
     * 
     * the problem is to say: given any tree, each tree node have a next node, you need to connect them with next pointer if they are on same level
     * 
     * we use level start as the travel pointer for each level while dummy.next maintain the next level head
     * 
     * dry run following tree 
         1
        /  \
       2    3
      /      \
     4        7

    
first level.next = 2, then  level will point 2 
then we found 1.right is not null, so level will point to 3 and move to 3

this round is done, then cur will move to its next, here is empty since root 

so outter loop, cur will point to 2, then new level will point 4, 

then cur will move to its next here is 3, then we can mark 4.next = 7, then level point to 7
     */
    
    //with the help of two assist nodes, we connect all nodes on each level, 
    //
    public void connect2(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode cur = root;
        while (cur != null) {
            TreeLinkNode dummy = new TreeLinkNode(0);
            TreeLinkNode levelStart = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    levelStart.next = cur.left;
                    levelStart = levelStart.next;
                }
                if (cur.right != null) {
                    levelStart.next = cur.right;
                    levelStart = levelStart.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
    }
}
