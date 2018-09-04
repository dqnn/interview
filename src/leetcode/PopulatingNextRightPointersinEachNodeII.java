package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PopulatingNextRightPointersinEachNodeII
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 117. Populating Next Right Pointers in Each Node II
 */
public class PopulatingNextRightPointersinEachNodeII {
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
    
public void connect2(TreeLinkNode root) {
        
        while(root != null){
            TreeLinkNode tempChild = new TreeLinkNode(0);
            TreeLinkNode currentChild = tempChild;
            while(root!=null){
                if(root.left != null) { currentChild.next = root.left; currentChild = currentChild.next;}
                if(root.right != null) { currentChild.next = root.right; currentChild = currentChild.next;}
                root = root.next;
            }
            root = tempChild.next;
        }
    }
}
