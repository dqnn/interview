package hatecode._1000_1999;
public class _1151MinimumSwapsToGroupAll1sTogether {
/*
1151. Minimum Swaps to Group All 1's Together
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.

 

Example 1:

Input: [1,0,1,0,1]
Output: 1
*/

    //thinking process: O(n)/O(1)
    
    //given one array, which only contains 0 or 1, then return the min swaps so all 1
    //are together, [1,0,1,0,1]->1, swap [0] and [4]
    
    //so suppose a window of all 1 length n, so we can use this window to count how many
    //1 in A also in this window. then the move is n - max, max is count of 1s in the window
    public int minSwaps(int[] A) {
        if(A.length < 3) return 0;
        int n = 0;
        for(int num: A){
            if(num == 1) n++; // total number of 1s
        }
        int l=0, r=0, c=0, max=0; //sliding window i to j
        while(r < A.length) {
            while(r < A.length && r - l < n){ //until i to j == n or search is done
                if(A[r++] == 1) c++;
            }
            max = Math.max(c, max); // max all the sliding window of which length equals to n
            if(r == A.length) break;
            
            if(A[l++] == 1) { //move i forward
                c--;
            }
        }
        return n - max; //this is the minimun swaps  
    }
}