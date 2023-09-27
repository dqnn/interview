package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : QueueReconstructionbyHeight
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 406. Queue Reconstruction by Height
 Suppose you have a random list of people standing in a queue. Each person is described by 
 a pair of integers (h, k), where h is the height of the person and k is the number of people in 
 front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
/*
 Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of 
 people taller than them, therefore each guy's index will be just as same as his k value.
For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
E.g.
input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
subarray after step 1: [[7,0], [7,1]]
subarray after step 2: [[7,0], [6,1], [7,1]]
 */
public class _406QueueReconstructionbyHeight {
    /*
     * thinking process: O(nlgn)/O(1)
     * 
     * the problem is to say: given 2D array A, A[i] = [h, k], h is height and k means how many poeple are taller or equals to this guy from 
     * left. so reconstuct the array
     * 
     * we sort the array by height desc first , if height equals, we sort by the k, from small to big
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]] --->
     * [[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]] sort by height desc, if same height, sort by k from small to big, since it means how many taller people left
     * 
     * then we start from left, add the element at cur[1], [6,1] will override [7,1], [7,1] will be on its right. which make sense
     * 
     * 
     */
    public static int[][] reconstructQueue(int[][] p) {
        if (p == null || p.length < 1 || p[0].length < 1) {
            return p;
        }
        List<int[]> res = new ArrayList<>();
        //after this, smaller height will be in back
        Arrays.sort(p, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        for(int[] cur : p){
            // how many people before this guy who has greater or equals height to this guy
            res.add(cur[1],cur);
        }

        return res.toArray(new int[p.length][2]);
    }


    public static void main(String[] args) {
        System.out.println(reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}));
    }
}
