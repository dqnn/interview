package hatecode._0001_0999;

import java.util.*;
public class _919CompleteBinaryTreeInserter {
/*
 * 919. Complete Binary Tree Inserter
A complete binary tree is a binary tree in which every level, except possibly the last, 
is completely filled, and all nodes are as far left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, 
and returns the value of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.
 

Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]
 */

 /*
  * thinking process: O()



  */
    TreeNode root;
    Queue<TreeNode> queue;
    public _919CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
      
        queue = new LinkedList<>();
        queue.offer(root);

        // BFS to populate deque
        while (!queue.isEmpty()) {
           
            TreeNode node =  queue.peek();
            if (node.left == null || node.right == null) {
                if(node.left !=null) {
                    queue.offer(node.left);
                }
                break; 
            }
            queue.poll();  
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public int insert(int v) {
        TreeNode node = queue.peek();
        TreeNode newNode = new TreeNode(v);
        queue.add(newNode);
        if (node.left == null)
            node.left = newNode;
        else {
            queue.poll();
            node.right= newNode;
        }

        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

class CBTInserter_DeQue {
    TreeNode root;
    Deque<TreeNode> deque;
    public CBTInserter_DeQue(TreeNode root) {
        this.root = root;
        deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // BFS to populate deque
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null)
                deque.offerLast(node);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peekFirst();
        deque.offerLast(new TreeNode(v));
        if (node.left == null)
            node.left = deque.peekLast();
        else {
            node.right = deque.peekLast();
            deque.pollFirst();
        }

        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

class CBTNode {
        TreeNode node;
        CBTNode next;
        CBTNode left;
        CBTNode right;
        public CBTNode(TreeNode node) {
            this.node = node;
        }
    }
    class CBTInserter_O1 {
        CBTNode cbtroot;
        CBTNode prevNode;
        public CBTInserter_O1(TreeNode root) {
            traverse(root);
        }
        public void traverse(TreeNode root) {
            Queue < TreeNode > q = new LinkedList < > ();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
                if (cbtroot == null) {
                    cbtroot = new CBTNode(node);
                    cbtroot.next = cbtroot;
                    prevNode = new CBTNode(new TreeNode(0));
                    prevNode.next = cbtroot;
                } else {
                    insertNode(node);
                }
            }
        }
        public int insertNode(TreeNode n) {
            if (prevNode.next.left == null) {
                prevNode.next.node.left = n;
                CBTNode cleft = new CBTNode(n);
                prevNode.next.left = cleft;
                if (prevNode.right == null) {
                    cleft.next = prevNode.next;
                    prevNode.next.next = cleft;
                } else {
                    CBTNode temp = prevNode.right.next;
                    prevNode.right.next = cleft;
                    cleft.next = temp;
                }
                return prevNode.next.node.val;
            } else {
                CBTNode cright = new CBTNode(n);
                prevNode.next.right = cright;
                prevNode.next.node.right = n;
                CBTNode temp = prevNode.next.left.next;
                prevNode.next.left.next = cright;
                cright.next = temp;
                prevNode = prevNode.next;
                return prevNode.node.val;
            }
        }
        public int insert(int v) {
            TreeNode n = new TreeNode(v);
            return insertNode(n);
        }
        public TreeNode get_root() {
            return cbtroot.node;
        }
    }