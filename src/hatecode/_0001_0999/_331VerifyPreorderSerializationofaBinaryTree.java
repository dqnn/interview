package hatecode._0001_0999;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : VerifyPreorderSerializationofaBinaryTree
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 331. Verify Preorder Serialization of a Binary Tree
 */
public class _331VerifyPreorderSerializationofaBinaryTree {
    /**
One way to serialize a binary tree is to use pre-order traversal. 
When we encounter a non-null node, 
we record the node's value. If it is a null node, we record using a sentinel 
value such as #.

          _9_
         /   \
        3     2
       / \   / \
      4   1  #  6
     / \ / \   / \
     # # # #   # #

     For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

     Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. 
     Find an algorithm without reconstructing the tree.

     Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

     You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

     Example 1:
     "9,3,4,#,#,1,#,#,2,#,6,#,#"
     Return true

     Example 2:
     "1,#"
     Return false

     Example 3:
     "9,#,#,1"
     Return false

     all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
     all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

     diff = outdegree - indegree

     time : O(n)
     space : O(n)

     * @param preorder
     * @return
     */
    // thinking process:
/*
Some used stack. Some used the depth of a stack. Here I use a different perspective. In a binary tree, 
if we consider null as leaves, then

all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
Suppose we try to build this tree. During building, we record the difference between out degree and in degree 
diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an 
in degree. If the node is not null, we increase diff by 2, because it provides two out degrees. If a serialization is 
correct, diff should never be negative and diff will be zero when finished.
 */
    
    //diff = out degree - in degree, finally it should be 0
    
    // only need to think about two use cases: ordinary and leaf nodes: 
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() < 1) return true;
        String[] nodes = preorder.split(",");
        // initialize the diff value as 1, so root node can be handled as common nodes
        int diff = 1;
        for (String node : nodes) {
            //next node is always child, so we bring input degree, so we minus 1,
            //if it is #, then it would only -1, which means every node it would -1
            if (--diff < 0) {
                return false;
            }
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }
    
    public boolean isValidSerialization2(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null || preorder.length() < 1) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            /*
                   4
                  / \
                 #   #
             this loop first add left # into stack, then right # comes, we found 
             stack.peek() already is # then we need to pop left #, parent 4. 
             */
            // this loop is mainly used in visiting tree, it provides a way to 
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop(); //eject parent node, 
            }
            st.push(curr);
        }
        //at last we put 6 right # into stack.
        return st.size() == 1 && st.peek().equals("#");
    }
}
