package leetcode;



public class InOrderAndPreOrderToPostOrder {

    /*
     * 
     * PreOrder: GDAFEMHZ InOrder: ADEFGHMZ PostOrder: AEFDHZMG
     * 现在，假设仅仅知道前序和中序遍历，如何求后序遍历呢？比如，
     * 已知一棵树的前序遍历是”GDAFEMHZ”，而中序遍历是”ADEFGHMZ”应该如何求后续遍历? PreOrder: GDAFEMHZ InOrder:
     * ADEFGHMZ first find G in inOrder and the part before G is ADEF its left tree
     * and HMZ is right tree recursive ADEF with DAFEMHZ and HMZ so we can know D is
     * root then A with AFEMHZ, and A is root on left.
     */

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        char     value;

        public TreeNode(char c) {
            value = c;
        }
    }
    public static void inOrderAndPreOrderToPostOrder(String in, String pre) {
        if (in == null || pre == null || in.length() != pre.length() || in.length() < 1) {
            return;
        }

        TreeNode root = setUpBTree(in, 0, in.length() - 1, pre, 0, pre.length() - 1);
        // postOrder to print the tree
        postOrderVisit(root);

    }

    public static void postOrderVisit(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderVisit(node.left);
        postOrderVisit(node.right);
        System.out.print(node.value);
    }

    public static TreeNode setUpBTree(String in, int inIdx, int inEnd, String pre, int preIdx, int preEnd) {
        // this is the key, when to exit
        // we cannot use '=' because we use length() -1 in the function, we need next
        // recursive
        // stack to return.
        // thinking about binary search exit conditions: low <= high.
        if (in == null || pre == null || inIdx > in.length() - 1 || preIdx > pre.length() - 1 || inIdx > inEnd
                || preIdx > preEnd) {
            return null;
        }
        int rootIdx = inIdx;
        for (int i = inIdx; i <= inEnd; i++) {
            if (in.charAt(i) == pre.charAt(preIdx)) {
                rootIdx = i;
                break;
            }
        }
        TreeNode node = new TreeNode(pre.charAt(preIdx));
        // this is the Key,
        // In will be split two parts ADEF and HMZ, G is rootIdx, so In(inIdx, rootIdx -
        // 1)
        // for Pre: you need remove G, and for left should start +1 and end at same
        // length that In indicates:
        // pre + rootIdx - inIdx + 1,
        // for right child, node, In : is start right after G, end is the whole string
        // of In
        // for Pre, starting is preIdx + rootIdx - inIdx + 1, thinking about the model
        // binary search
        node.left = setUpBTree(in, inIdx, rootIdx - 1, pre, preIdx + 1, preIdx + rootIdx - inIdx);
        node.right = setUpBTree(in, rootIdx + 1, inEnd, pre, preIdx + rootIdx - inIdx + 1, preEnd);
        return node;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        inOrderAndPreOrderToPostOrder("ADEFGHMZ", "GDAFEMHZ");
    }

}
