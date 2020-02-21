package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : hatecode
 * Package Name : hatecode
 * File Name : NextPermutation
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class _031NextPermutation {
    /**
     * 31. Next Permutation
     * Here are some examples. Inputs are in the left-hand column and 
     * its corresponding outputs are in the right-hand column.
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1

     // 1　　2　　7　　4　　3　　1
             ^
     // 1　　2　　7　　4　　3　　1
                          ^
     // 1　　3　　7　　4　　2　　1
             ^            ^
     // 1　　3　　1　　2　　4　　7
                 ^   ^    ^   ^

     7 4 3 2 1 1

     time : O(n);
     space : O(1);
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int firstSmall = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmall = i;
                break;
            }
        }

        if (firstSmall == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int firstLarge = -1;
        for (int i = nums.length - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }
        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
        return;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
    //interview friendly: 
    //thinking process: the problem is to find next permutation if they all sorted, if last one
    //return first permutation
    
    //1: if it is decreasing, then next is the increase sequence
    //2: 
    //e.g. {1,2,7,4,3,1}
    //first we want to find adjacent two digits left is smaller than right
    
    public static void nextPermutation2(int[] nums) {
        //edge case
        if (nums == null || nums.length == 1) return;

        int end = nums.length -2;
        int j = end;
        //we find the the pattern like 1,2,3, 
        //note j point to 2 and stop
        for(; j>=0 && nums[j+1] <= nums[j];) {
            j--;
        }
        //this means the all array is desc, we just need to reverse it
        if(j < 0) { // it sorted desc
            Arrays.sort(nums);
            return;
        }
        //here is the key: we scan from back to j, if we find one number 
        //which is bigger than j, then we swap and sort
        for(int i =end+1; i>j;i--) {
            if(nums[j] < nums[i]) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i]  = temp;
                Arrays.sort(nums, j+1, end+2);
                break;
            }
        }
    }
    //
    public static void main(String[] args) {
        int[] in = {1,2,7,4,3,1}; // [1, 3, 1, 2, 4, 7]
        nextPermutation2(in);
        System.out.println(Arrays.toString(in));
    }
}
