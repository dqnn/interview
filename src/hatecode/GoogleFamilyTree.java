package hatecode;

import java.util.*;
public class GoogleFamilyTree {
/*
 * From google interview doc, not from LC
 * problem statement:
 * 
 * 在family tree中找两个人是否是亲戚。follow up如果是亲戚的话，找lowest common ancestor。
第三题大概这样
class Node {
int val,
Node father;
Node mother;
}
public boolean isRelated(Node p1, Node p2);
follow up: findLowestCommonAncester(Node p1, Node p2);

 */
    class Node {
        int val;
        Node father;
        Node mother;
    }

    public boolean isRelated(Node p1, Node p2) {
        Set<Node> ancesters = new HashSet<>();
        // BFS
        Queue<Node> queue = new LinkedList<>();
        queue.offer(p1);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.father != null)
                queue.offer(curr.father);
            if (curr.mother != null)
                queue.offer(curr.mother);
            ancesters.add(curr);
        }
        queue = new LinkedList<>();
        queue.offer(p2);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (ancesters.contains(curr))
                return true;
            if (curr.father != null)
                queue.offer(curr.father);
            if (curr.mother != null)
                queue.offer(curr.mother);
        }
        return false;
    }
//follow up:findLowestCommonAncester, LC 236. Lowest Common Ancestor of a Binary Tree
    public Node findLowestCommonAncester(Node p1, Node p2) {
        Set<Node> ancesters = new HashSet<>();
        // BFS
        Queue<Node> queue = new LinkedList<>();
        queue.offer(p1);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.father != null)
                queue.offer(curr.father);
            if (curr.mother != null)
                queue.offer(curr.mother);
            ancesters.add(curr);
        }
        queue = new LinkedList<>();
        queue.offer(p2);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (ancesters.contains(curr))
                return curr;
            if (curr.father != null)
                queue.offer(curr.father);
            if (curr.mother != null)
                queue.offer(curr.mother);
        }
        return null;
    }
}
