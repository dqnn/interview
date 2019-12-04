package hatecode;

import java.util.*;
public class _1104PathInZigzagLabelledBinaryTree {
/*
1104. Path In Zigzag Labelled Binary Tree
In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.

Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

 

Example 1:

Input: label = 14
Output: [1,3,4,14]
*/
    //thinking process:O(lgn/lgn)
    
    //given an infinite binary tree, each row will have 2^(n-1) nodes, n = 1,2..., 
    //odd row, start from right, even start from left, so given a node value, return the root from root to this node, 
    //for example, 14-->return [14,4,3,1]
    
    //
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new LinkedList<>();
        
        //find the max of the level where label located
        int max = 1;
        while(label > max) max = max*2 + 1;

        //find the reverseLabel, E.g. label = 14, then the reverseLabel is 9
        //we can pick one from label / 2
        //max/2 + 1: row starts value which row max is in, max is last node in that row
        //max - label: count of nodes between max and label, 
        //so reverseLabel means if starts from reverse, where it should be
        int reverseLabel = (max / 2 + 1) + max - label;
        //if label = 14,  max = 15, reverseLable = 9
        //so we route from leaf to root, each time we swap reverse label and 
        // label
        while(label != 0 && reverseLabel != 0){
            res.add(0, label);          //pick 14, 9/2,14/2/2,9/2/2/2 which is 14,4,3,1
            label /= 2;
            reverseLabel /= 2;
            //swap reverseLabel and level
            int temp = label;
            label = reverseLabel;
            reverseLabel = temp;
        }
        return res;
    }
}