package hatecode._1000_1999;
public class _1295FindNumberswithEvenNumberOfDigits {
/*
1295. Find Numbers with Even Number of Digits
    Given an array nums of integers, return how many 
    of them contain an even number of digits.

 

Example 1:

Input: nums = [12,345,2,6,7896]
Output: 2
*/
    //thinking process: 
    //given an integer array, return how many contain an even number of 
    //digits
    public int findNumbers(int[] A) {
        
        int count=0;
        for(int i =0 ; i< A.length; i++){
            
            if(A[i]>9 && A[i]<100
               || A[i]>999 && A[i]<10000
               || A[i]==100000){
                count++;
            }
        }
        
        return count;
        
    }
}