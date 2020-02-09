package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : EvaluateDivision
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 399. Evaluate Division
 */
public class EvaluateDivision {

    /** tags: google, facebook
     * Equations are given in the format A / B = k, where A and B are variables represented as strings,
     * and k is a real number (floating point number). Given some queries, return the answers.
     * If the answer does not exist, return -1.0.

     a / b * b / c = a / c

     Example:
     Given a / b = 2.0, b / c = 3.0.
     queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
     return [6.0, 0.5, -1.0, 1.0, -1.0 ].

     The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
     where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

     According to the example above:

     equations = [ ["a", "b"], ["b", "c"] ],
     values = [2.0, 3.0],
     queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

     equation : "b", "c"

     map : "a" 'b', 2.0
           "b" 'a', 1/2.0
               'c', 3.0
           "c" 'b', 1/3.0

     time : O(V+E)
     space : O(n)
Image a/b = k as a link between node a and b, the weight from a to b is k, 
the reverse link is 1/k. Query is to find a path between two nodes.

     */

    class Node{
        String d;
        double val;
        Node(String d, double val) {
            this.d = d;
            this.val = val;
        }
    }
    private Map<String, List<Node>> map = new HashMap<>();
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //edge case
        //add all related nodes into this graph.
        if (equations == null || equations.length < 1 || values == null || queries == null || values.length < 1 || equations.length != values.length) {
            return null;
        }
        //add a/b and b/a into the map, note, value is List<Node>
        for(int i = 0; i < values.length; i++) {
            String[] equation = equations[i];
            map.computeIfAbsent(equation[0], v->new ArrayList<>());
            map.get(equation[0]).add(new Node(equation[1], values[i]));
            //for division
            map.computeIfAbsent(equation[1], v->new ArrayList<>());
            map.get(equation[1]).add(new Node(equation[0], 1/values[i]));
        }
        
         // calculate the queries
        double[] res = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            res[i] = find(query[0], query[1], 1.0, new HashSet<String>());
        }
        //return the result
        return res;
    }
    //ld is src, d is end, so we need to add ld into visited when we visit
    //if we can find it, then we shoud makr it visited, then go into the loop
    public double find(String ld, String d, double value, Set<String> visited) {
        if (visited.contains(ld)) return -1;
        if (!map.containsKey(ld)) {
            return -1;
        }
        if (ld.equals(d)) return value;
        
        visited.add(ld);
        for(Node node : map.get(ld)) {
            double sub = find(node.d, d, value * node.val, visited);
            if (sub != -1) {
                return sub;
            }
        }
        visited.remove(ld);
        return -1;
    }
}

