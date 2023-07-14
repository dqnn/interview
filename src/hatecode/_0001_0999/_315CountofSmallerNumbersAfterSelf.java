package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountofSmallerNumbersAfterSelf
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 315. Count of Smaller Numbers After Self
 */
public class _315CountofSmallerNumbersAfterSelf {
    /**
     * You are given an integer array nums and you have to return a new counts array.
     * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

     Example:

     Given nums = [5, 2, 6, 1]
π
     To the right of 5 there are 2 smaller elements (2 and 1).
     To the right of 2 there is only 1 smaller element (1).
     To the right of 6 there is 1 smaller element (1).
     To the right of 1 there is 0 smaller element.
     Return the array [2, 1, 1, 0].

     [5, 2, 6, 1]

     int[] Arrays.asList()
     0 1 1 2

     time : O(n^2)
     space : O(n)
     */

    // we maintain array list,
    // and we loop from array end, and added to list
    //
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(list, nums[i]);
            res[i] = index;
            list.add(index, nums[i]);
        }
        return Arrays.asList(res);
    }

    // insert sort and find the correct position, it returns the
    // position that target should be, it is asc
    // and so end is the number how many numbers are there smaller than target
    // binary insert sort
    private int findIndex(List<Integer> list, int target) {
        if (list.size() == 0) return 0;
        int start = 0;
        int end = list.size() - 1;
        if (list.get(end) < target) return end + 1;
        if (list.get(start) >= target) return 0;
        // 
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (list.get(start) >= target) return start;
        return end;
    }


    //below is to utilize SegmentTree to solve the problem
    /*
     * each node will have s(start), e(end) and count (count of integers)
     * 
     * A = [5,2,6,1]
     * 
     * we utilize segment tree to remember how many numbers are small than x 
     * we define 
     *  Node {
     *  int s, e, count;
     *  Node l, r;
     * }
     * 
     * when we build the tree, we 
     * 
     * 
     */
    class Node{
        int s, e, count;
        Node l, r;
        public Node(int s, int e) {
            this.s =s;
            this.e =e;
        }
    }
    
    class SegmentTree{
        Node root;
        public SegmentTree(int s, int e) {
            this.root = buildTree(s, e);
        }
        
        private Node buildTree(int s, int e) {
            if (s > e) return null;
            Node node = new Node(s, e);
            if (s == e) {
                node.count = 0;
            }
            else {
                int m = s + (e -s)/2;
                node.l = buildTree(s, m);
                node.r = buildTree(m+1, e);
            }
            
            return node;
        }
        
        public int query(Node root, int s, int e) {
            if (root == null || s > e) return 0;
            else if (root.s == s && root.e == e) return root.count;
            else {
                int m = root.s + (root.e - root.s)/2;
                if (e <= m) return query(root.l, s, e);
                else if (s >= m) return query(root.r, s, e);
                else return query(root.l, s, m) + query(root.r, m + 1, e);
            }
            
        }
        
        public void update(Node root, int i, int v) {
            if (root.s == i && root.e == i) {
                root.count+=1;
                return;
            }
            
            int m = root.s + (root.e - root.s)/2;
            if (root.s <=i && i <= m) {
                update(root.l, i, v);
            }
            
            if (m < i && i<=root.e) {
                update(root.r, i, v);
            }
            
            root.count= root.l.count + root.r.count;
        }
    }
    
    public List<Integer> countSmaller_SegmentTree(int[] A) {
        if (A == null || A.length < 1) return new ArrayList<>();
        
        int[] res = new int[A.length];
        int min = A[0], max=A[0];
        for(int a : A) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        SegmentTree tree = new SegmentTree(min, max);
        for(int i = A.length-1; i>=0; i--) {
            res[i] = tree.query(tree.root, min, A[i]-1);
            tree.update(tree.root, A[i], 1);
        }
        
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }
}
