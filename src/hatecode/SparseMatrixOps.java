package hatecode;

import java.util.*;

public class SparseMatrixOps {
//sparse matrix means most elements in matrix are 0, 
/*
A = 
2.0 -1.0    0   0
-1.0    2.0 -1.0    0
0   -1.0    2.0 -1.0
0   0   -1.0    2.0

A = [[(0, 2.0), (1, -1.0)],
     [(0, -1.0), (1, 2.0), (2, -1.0)],
     [(1, -1.0), (2, 2.0), (3, -1.0)],
     [(2, -1.0), (3, 2.0)]]

 */
    class Node {
        int j;
        int val;
        public Node(int j, int val) {
            this.j = j;
            this.val= val;
        }
    }
    List<List<Node>> m;
    
    public SparseMatrixOps() {
        this.m = new ArrayList<>();
    }
    public void set(int i, int j, int value) {
        if (m.get(i) == null) {
            m.add(new ArrayList<>());
        }
        Node  node = getNode(i, j);
        node.val = value;
        m.get(i).set(j, node);
    }
    
    public int get(int i, int j) {
        Node node =getNode(i, j);
        if (node == null) return 0;
        else return node.val;
    }
    
    public void add(int i, int j, int value) {
        if (m.get(i) == null) {
            m.add(i, new ArrayList<>());
        } 
        m.get(i).add(new Node(j,value));
    }
    
    private Node getNode(int i, int j) {
        if (m.get(i) == null) return null;
        if (m.get(i).size() <= j) return null;
        return m.get(i).get(j);
    }
}
