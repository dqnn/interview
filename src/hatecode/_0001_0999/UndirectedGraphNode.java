package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UndirectedGraphNode
 * Date : Sep, 2018
 * Description : TODO
 */
public class UndirectedGraphNode {

    public int val;
    public List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        val = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
