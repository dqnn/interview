package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumHeightTrees
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 310. Minimum Height Trees
 */
public class MinimumHeightTrees {
    /**
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is 
then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height 
trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of 
undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the 
same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Solution

     0  1  2
      \ | /
        3
        |
        4
        |
        5
     return [3, 4]

     0 : 3
     1 : 3
     2 : 3
     3 :    4
     4 : 3
     5 : 4

     1 -- 2 -- 3

     time : O(n)
     space : O(n)

     * @param n
     * @param edges
     * @return
     */
    
    // there is similiar question before, that node is the center and we walk out step by step until end, 
    // so we count how many steps we walked, 
    /*
发现大家推崇的方法是一个类似剥洋葱的方法，就是一层一层的褪去叶节点，最后剩下的一个或两个节点就是我们要求的最小高度树的根节点，
这种思路非常的巧妙，而且实现起来也不难，跟之前那到课程清单的题一样，我们需要建立一个图g，是一个二维数组，其中g[i]是一个一维数组，
保存了i节点可以到达的所有节点。我们开始将所有只有一个连接边的节点(叶节点)都存入到一个队列queue中，然后我们遍历每一个叶节点，
通过图来找到和其相连的节点，并且在其相连节点的集合中将该叶节点删去，如果删完后此节点也也变成一个叶节点了，
加入队列中，再下一轮删除。那么我们删到什么时候呢，当节点数小于等于2时候停止，
此时剩下的一个或两个节点就是我们要求的最小高度树的根节点啦
     */
 // this is simplified version, map should be better than set
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Integer> res = new ArrayList<>();
        // we have Set in List
        List<HashSet<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        //  first node number is the list index
        // second is the anothe node so as a map
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // if one set only has one elment means it is a leaf node
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                res.add(i);
            }
        }
        
        // if n nodes more than 2,if only 1, then we find it
        while (n > 2) {
            //how many non-leaf nodes
            n = n - res.size();
            List<Integer> leaves = new ArrayList<>();
            // we visit all leaf nodes
            for (int i : res) {
                // connect to leaf nodes
                for (int j : adj.get(i)) {
                    //we remove the leaf nodes from set
                    adj.get(j).remove(i);
                    // we detect the node become leaf, like we remove leaf nodes gradully
                    if (adj.get(j).size() == 1) {
                        leaves.add(j);
                    }
                }
            }
            res = leaves;
        }
        return res;
    }
}
