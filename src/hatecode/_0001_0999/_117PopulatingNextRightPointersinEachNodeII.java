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
