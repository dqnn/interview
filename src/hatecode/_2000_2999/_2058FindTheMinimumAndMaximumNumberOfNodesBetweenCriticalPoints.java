
package _2000_2999;

import java.util.*;

public class _2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
    /*
    2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
    A critical point in a linked list is defined as either a local maxima or a local minima.
    
    A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
    
    A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
    
    Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
    
    Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, return [-1, -1].
    
     
    
    Example 1:
    
    
    Input: head = [3,1]
    Output: [-1,-1]
    */
    /*
     * thinking process: O(n)/O(n)
     * the problem is to say: given one linked list, define two concepts first, one is local max, 
     * i < A > j,A is local max, 
     * i > A < j, A is local min, 
     * 
     * then find all numbers in list A, return its min distance and max distance for local min or max
     * 
     * add indexes to list and calculate, since it is monotonic list, so min must be 2 adjacent elements, 
     * max is current - min(list)
     * 
     * one optimization is to add 2nd loop to 1st one, 
     */
        public int[] nodesBetweenCriticalPoints(ListNode A) {
            if (A == null) return new int[]{-1,-1};
            
            List<Integer> list = new ArrayList<>();
            
            ListNode prev = A;
            A = A.next;
            int idx = 1;
            while(A.next != null) {
                
                if (prev.val < A.val && A.val > A.next.val || prev.val > A.val && A.val < A.next.val) {
                    list.add(idx);
                }
                
                prev = A;
                A = A.next;
                idx++;
                
            }
            
            if (list.size() < 1) return new int[]{-1,-1};
            //System.out.println(list);
           
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int s = list.get(0);
            for(int i = 1; i<list.size(); i++) {
                min = Math.min(min, list.get(i) - list.get(i-1));
                s = Math.min(s, list.get(i));
                max = Math.max(max, list.get(i) - s);
            }
            
            return new int[]{min == Integer.MAX_VALUE ? -1 : min, max==Integer.MIN_VALUE? -1:max};
        }
    }