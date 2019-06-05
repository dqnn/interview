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
    
    private static void helper(ColorNode node, List<ColorNode> res) {
        if (node == null) return;
        if (node.isRed) {
            for(ColorNode child: node.children) {
                if (!child.isRed) res.add(child);
                helper(child, res);
            }
        } else {
            res.add(node);
            Iterator<ColorNode> it = node.children.iterator();
            while(it.hasNext()) {
                ColorNode child = it.next();
                if (child.isRed) it.remove();
                helper(child, res);
            }
        }
    }
    
    public static void main(String[] args) {
        ColorNode root = new ColorNode(1, true);
        
    
    }

}
