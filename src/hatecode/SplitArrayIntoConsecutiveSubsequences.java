package hatecode;
import java.util.*;
public class SplitArrayIntoConsecutiveSubsequences {
/*
659. Split Array into Consecutive Subsequences
You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5
*/
    //thinking process: the problem is to say given a list of integer, whether we can split into 
    //consecutive sub sequence, each subsequence length >=3, the array is asc sorted
    //[1,2,3,3,4,4,5,5]
    //1->1, 2->1, 3->2, 4->2, 5->2
    
    // for each subarray, we can have two formats, one is like 3 consecutive
    // numbers, another is more than 3, so
    // when we visit the array we can remember what we have visited and according to
    // current array status, we add possible
    // qualified candidate number and frequency into the map, so later when we visit
    // the candidate, we can know previous sequence
    // need this number, each visit will produce this number
    
    //so for the problem specifically, we use one map to store number->fre, another map is to store the possible append number->freq, 
    // when we visit the array, we detect whether its fre == 0, yes then we continue, if not we will try 
    //to find whther it is possible to append previous consective sequence, if not, whether 
    //it is start a new sequence. this is greedy thinking, we want to move as many numbers as we can 
    //in one sequence
    //append priority is higher than starting new sequence, since greedy algorithms, 
    //example like [1,2,3,3,4,4,5,6,7,7,8,9]
    //if we change the order, it would be false actually it can split into 
    //1,2,3,4 | 3,4,5,6,7 | 7, 8, 9
    //if we running this array, it will return false 
    
    //this is two sum map solution extension, we added the candidate we want into map for later visit
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 1) return false;
        //keep how many numbers are there
        Map<Integer, Integer> num2FreMap = new HashMap<>();
        for(int num : nums) num2FreMap.put(num, num2FreMap.getOrDefault(num, 0) + 1);
        //candidateToAppendPreSeqMap means the possible that qualified numbers and frequency we need for previous sequence
        Map<Integer, Integer> candidateToAppendPreSeqMap = new HashMap<>();
        
        //second pass, 
        for(int i : nums) {
            if (num2FreMap.get(i) == 0) continue;
            //this is trying to append pre consective sequence  and we cannot change the if else 
            //order, this has to be before the starting real sequence order
            if (candidateToAppendPreSeqMap.getOrDefault(i, 0) > 0) {
                candidateToAppendPreSeqMap.put(i, candidateToAppendPreSeqMap.get(i) - 1);
                candidateToAppendPreSeqMap.put(i + 1,  candidateToAppendPreSeqMap.getOrDefault(i + 1, 0) + 1);
            //we found one candidate subarray, and add i + 3  fre + 1 into candidatePool, so next time when we encounter
            // the num, so we can get it out and use it
            } else if (num2FreMap.getOrDefault(i+1,0) > 0 && num2FreMap.getOrDefault(i+2,0) > 0) {
                num2FreMap.put(i+1, num2FreMap.get(i+1) - 1);
                num2FreMap.put(i+2, num2FreMap.get(i+2) - 1);
                candidateToAppendPreSeqMap.put(i+3, candidateToAppendPreSeqMap.getOrDefault(i+3,0) + 1);
            } else return false;
            //default, we reduce i fre by 1
            num2FreMap.put(i, num2FreMap.get(i) - 1);
        }
        return true;
    }
}