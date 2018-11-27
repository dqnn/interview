package leetcode;

import java.util.Arrays;

public  class BuddySystemBitMap {
/*
 * Pure Storage interview questions
   Given a complete binary tree with nodes of values of either 1 or 0, the following 
   rules always hold:
    (1) a node's value is 1 if and only if all its subtree nodes' values are 1
    (2) a leaf node can have value either 1 or 0
    Implement the following 2 APIs:
    set_bit(offset, length), set the bits at range from offset to offset+length-1 to 1
    clear_bit(offset, length), clear the bits at range from offset to offset+length-1  to 0
    
    i.e. The tree is like:
                 0
              /     \
             0        1
           /  \      /  \
          1    0    1    1
         /\   / \   / 
        1  1 1   0 1
        Since it's complete binary tree, the nodes can be stored in an array:
        [0,0,1,1,0,1,1,1,1,1,0,1] 
 */
    public void set_bit(int[] t, int start, int len) {
       if (t == null || t.length< 1) return;
       int n = t.length -1, end = Math.min(n + 1, Math.min(start + len,  2 * start + 1));
       for(int i = start; i < end; i++) {
           if (t[i] == 1) continue;
           t[i] = 1;
           set_subTree(t, i, n);
           while(i> 0) {
               if (i % 2 == 0 && t[i-1] == 1 
                   || (i % 2 == 1 && i < n && t[i+1] == 1)) {
                   t[(i - 1)/2] = 1;
               }
               i = (i - 1)/2;
           }
       }
    }
    private void set_subTree(int[] t, int start, int len) {
        if (start > len) return;
        if (2 * start + 1 < len  && t[2* start + 1] == 0) {
            t[2* start + 1] = 1;
            set_subTree(t, 2 * start + 1, len);
        }
        if (2 * start + 2 < len  && t[2* start + 2] == 0) {
            t[2* start + 2] = 1;
            set_subTree(t, 2 * start + 2, len);
        }
    }
    
    public void clear_bit(int[] t, int start, int len) {
        if (t == null || t.length < 1 || start < 0 || len < 0) return;
        int n = t.length -1, end = Math.min(n + 1, start + len);
        for(int i = start; i< end;i++) {
            if (t[i] == 0) continue;
            t[i] = 0;
            //clear sub
            while(2 * i + 1 < n) {
                t[2*i + 1] = 0;
                i = 2 * i + 1;
            }
            //clear ancestors
            while(i > 0) {
                if (t[(i -1) /2] == 0) break;
                t[(i-1)/2] = 0;
                i = (i-1)/2;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] t = {0,0,1,1,0,1,1,1,1,1,0,1};
        BuddySystemBitMap buddy = new BuddySystemBitMap();
        buddy.set_bit(t, 1, 3);
        System.out.println(Arrays.toString(t));
    }
}
