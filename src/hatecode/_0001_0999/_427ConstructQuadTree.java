package hatecode._0001_0999;


public class _427ConstructQuadTree {
    /*
427. Construct Quad Tree
Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.

Return the root of the Quad-Tree representing grid.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represnts False and 1 represents True in the photo representing the Quad-Tree.
// Definition for a QuadTree node.

};
*/

/*
 * interview friendly: O(n)/O(n)
 * 
 * the problem is to say: given one matrix with 2^n elements, steps: 
 * 1. you can divide into 4 matrix, 
 * 2. if all elements are the same in one matrix, the create a Node withe Node.isLeaf = true and val set to true
 * 3. if not, then continue divide into 4 matrix until only 1 elements left in matrix, 
 * 
 * this way you do no have to store all elements in matrix, quad tree is one type of sparse matrix
 * 
 * it is recursive way to build he quad tree, we visit each cell only once 
 * 
 *  
 */
    public Node construct(int[][] g) {
        if (g == null || g.length < 1 || g[0].length < 1) return null;
        
        int r = g.length, c = g[0].length;
        Node root = helper(g, 0, 0, r, c);
        
        return root;
    }
    
    /*
     * top left 
     *         bottom right 
     * 
     * we use r and c not (r-1) and (c-1) so we do not handle the /2 problem
     */
    private Node helper(int[][] g, int x0, int y0, int x1, int y1) {
        if ((x0 + 1 == x1)  && (y0 + 1 == y1)) return new Node(g[x0][y0] ==1 , true);
        
        /*
          (x0, y0) 
                 
                  (x1, y1)

        
                  
          
          (x0, y0)                        (x0, (y0+y1)/2)
                   
                   ((x0+x1)/2, (y0+y1)/2)                  ((x0+x1)/2, y1)
             
         ((x0+x1)/2, y0)           ((x0+x1)/2, (y0+y1)/2) 
                     (x1, (y0+y1/2))                     (x1, y1)
          
        */
        Node topLeft = helper(g, x0, y0, (x0+x1)/2, (y0+y1)/2);
        Node topRight = helper(g, x0, (y0+y1)/2, (x0+x1)/2, y1);
        Node bottomLeft = helper(g, (x0+x1)/2, y0, x1, (y0+y1)/2);
        Node bottomRight = helper(g, (x0+x1)/2, (y0+y1)/2, x1, y1);
        
        Node res = new Node();
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
          && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            res.val = topLeft.val;
            res.isLeaf = topLeft.isLeaf;
            return res;
        }
        
        res.topLeft = topLeft;
        res.topRight = topRight;
        res.bottomLeft = bottomLeft;
        res.bottomRight = bottomRight;
        return res;
    }


    class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
}