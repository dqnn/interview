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
        
        static class Node{
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
            public SegmentTree(int[] A, int k){
                int min = A[0], max = min;
                for(int a : A) {
                    min = Math.min(min, a);
                    max = Math.max(max, a);
                }
                this.root = buildTree(0, max);
            }
            
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
            
            public void update(Node root, int i, int v) {
                if (root == null || i < root.s || i > root.e) return;
                
                if (root.s == i && root.e == i) {
                    root.count =v;
                    return;
                }
                
                int m = root.s + (root.e- root.s)/2;
                if (root.s <= i && i <= m) {
                    update(root.l, i, v);
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

            SegmentTree tree = new SegmentTree(clone, k);
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