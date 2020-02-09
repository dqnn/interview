package hatecode._0001_0999;

import java.util.*;
public class RetainAllRoots {
/*
 * this is from Goole interview doc, not from LC
 * 
 * Problem statement, given a N-Tree, which has only red and black nodes, now we would like to 
 *remove all red nodes, return a list<Node> which are all roots for new trees
 */
    
    public static class ColorNode {
        int val;
        List<ColorNode> children;
        boolean isRed;
        public ColorNode(int v, boolean isRed) {
            this.val = v;
            this.isRed = isRed;
        }
    }
    
    //
    public static List<ColorNode> find(ColorNode root){
        List<ColorNode> res = new ArrayList<>();
        helper(null, root, res);
        return res;
    }
    //each node passed to recursive function could be considered as root
    //we check the root, it is red or it is not red,discuss 
    //root can only be root when 
    //1. it is root node
    //2. parent is red, current root is not red. 
    //others will not be root
    private static void helper(ColorNode parent, ColorNode root, List<ColorNode> res) {
        if (root == null) return;
        
        if((parent == null || parent.isRed) && !root.isRed) {
            res.add(root);
        }
        for(ColorNode child: root.children) {
            helper(root, child, res);
        }
    }
    
    public static void main(String[] args) {
        ColorNode root = new ColorNode(1, true);
    }

}
