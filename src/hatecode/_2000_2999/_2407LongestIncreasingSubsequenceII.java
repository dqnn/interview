package hatecode._2000_2999;

import java.util.*;

public class _2407LongestIncreasingSubsequenceII {
    /*
    2407. Longest Increasing Subsequence II
    
    You are given an integer array nums and an integer k.
    
    Find the longest subsequence of nums that meets the following requirements:
    
    The subsequence is strictly increasing and
    The difference between adjacent elements in the subsequence is at most k.
    Return the length of the longest subsequence that meets the requirements.
    
    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
    
     
    
    Example 1:
    
    Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
    Output: 5
    */
        public int lengthOfLIS_DP(int[] A, int k) {
            if (A == null || A.length < 1) return 0;
            
            int n = A.length;
            int[] dp = new int[n];
            int res = 1;
            dp[0] = 1;
            for(int i = 1; i< n; i++) {
                dp[i] = 1;
                for(int j = 0; j< i; j++) {
                    if (A[i] - A[j]<=k && A[i]- A[j] > 0) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        res = Math.max(res, dp[i]);
                    }
                    
                }
            }
            
            return res;
        }
        
        /*
         * this is interview friendly for the best TC.
         * thinking process: O(nlgmax(A))/O(max(A))
         * 
         * the problem is to say: given one array A, and integer k, find the longest strictly(>) increasing sequence, return the max length of it.
         * in the sequence, adjacent element have max difference is k. 
         * 
         * for example: A=[4,2,1,2,3], k = 3 ---> 3,  res =[1,2,3]
         * 
         * since min query number will be negative, and binary search cannot be performed on negative number ([-3,-1]--> [-3,-2], [-1,-1]-->[-3,-2], [-1,-1])
         * 
         * so we can clone the array, A=[4,2,1,2,3]--> min = 1, max = 4, k = 3, so delta = min - k = 2, then we build the tree on [0,6]
         * 
         * then for each element, we query(root, A[i]-k, A[i]-1), it will return how many elements between [A[i]-k, A[i]-1] 
         * note: each node count property will store the max integer count for the node range
         * 
         * then we know how many numbers in range [A[i] -k, A[i]-1], then we update this number to tree with +1, update(root, i, v)  i is the number, v is the value
         * 
         * 
         * 
         * 
         * 
         * there are some edge cases and steps to utilize segment tree
         * 
         * 1. each node will have 5 elements, s, e, val(here is count),l and r. val is most important one
         * 2. each left node range [s, m], right [m+1, e]
         * 
         * there are 2 types of operations:
         * 1. query on one element and query range, this will include several examples:
         *  a. min, max on range
         *  b. query on one element
         * 
         * 
         * 
         *
         */
        static class Node{
            //count means the max count of integer for this node(range) which ends with the numbers in its [s, e]
            int s, e, count;
            Node l, r;
            public Node(int s, int e) {
                this.s=s;
                this.e=e;
            }
            
            public String toString(){
                return s+"-"+e+"-"+count;
            }
        }
        
        static class SegmentTree {
            Node root;
            public SegmentTree(int min, int max){
                this.root = buildTree(min, max);
            }
            
            //post order to build the tree, we did not assign value to count which will be assigned when visiting A
            public Node buildTree(int s, int e) {
                if (s > e) return null;
                Node node = new Node(s, e);
                if (s == e) {
                    return node;
                }
                
                int m = s +(e-s)/2;
                node.l = buildTree(s, m);
                node.r = buildTree(m+1, e);
                return node;
            }
            
            // post order to update the node, v is updated value, we can add or assign depends on the problem
            public void update(Node root, int i, int v) {
                if (root == null || i < root.s || i > root.e) return;
                
                if (root.s == i && root.e == i) {
                    root.count =v;
                    return;
                }
                
                int m = root.s + (root.e- root.s)/2;
                if (root.s <= i && i <= m) {
                    update(root.l, i, v);
                //note we have to use i > m cannot i >=m becuase m is not in righ branch
                } else if (m < i && i <=root.e) {
                    update(root.r, i, v);
                } 
                
                root.count = Math.max(root.l.count, root.r.count);
            }
            
            
            public int query(Node root, int l, int r) {
                if (root==null || l > root.e || r < root.s) return 0;
                
                if (root.s == l && root.e == r) return root.count;
                
                int m = root.s + (root.e-root.s)/2;
                if ( r <=m ) return query(root.l, l, r);
                // here l >m, not l >=m because m is only in left branch, for example [1,10], k =10. 
                else if (l > m) return query(root.r, l, r);
                else return Math.max(query(root.l, l, m), query(root.r, m +1, r));
            }
            
        }
        
        public static int lengthOfLIS(int[] A, int k) {
            if (A == null || A.length < 1) return 0;


            int min = A[0], max = min;
                for(int a : A) {
                    min = Math.min(min, a);
                    max = Math.max(max, a);
                }

                int delta = 0;
                if (min < k) {
                    delta =  k- min;
                }

                int[] clone = A.clone();
                for(int i = 0; i< A.length; i++) {
                    clone[i] += delta;
                }

            SegmentTree tree = new SegmentTree(min-k + delta, max+delta);
            int res = 1;

            for(int a : clone) {
                int prev = tree.query(tree.root, a-k, a-1);
                tree.update(tree.root, a, prev+1);
                res = Math.max(res, prev+1);
            }
            
            return res;
        
        }

        public static void main(String[] args) {
            System.out.println(lengthOfLIS(new int[]{4,2,1,4,3,4,5,8,15}, 3));
            System.out.println(lengthOfLIS(new int[]{7,4,5,1,8,12,4,7}, 5));
            System.out.println(lengthOfLIS(new int[]{1,10}, 10));
        }
         
    }