package hatecode;

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
        helper(root, res);
        return res;
    }
    //each node passed to recursive function could be considered as root
    //we check the root, it is red or it is not red,discuss 
    //
    private static void helper(ColorNode root, List<ColorNode> res) {
        if (root == null) return;
        if (root.isRed) {
            for(ColorNode child: root.children) {
                if (!child.isRed) res.add(child);
                helper(child, res);
            }
        } else {
            res.add(root);
            Iterator<ColorNode> it = root.children.iterator();
            while(it.hasNext()) {
                ColorNode child = it.next();
                //we have to remove the child nodes who are red
                if (child.isRed) it.remove();
                helper(child, res);
            }
        }
    }
    
    public static void main(String[] args) {
        ColorNode root = new ColorNode(1, true);
    }

}
