package hatecode._0001_0999;

import java.util.Arrays;
import java.util.Random;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RandomPickIndex
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class RandomPickIndex {
    /**
     * 398. Random Pick Index
     * Given an array of integers with possible duplicates, randomly output the index of a given target
     * number. You can assume that the given target number must exist in the array.

     Note:
     The array size can be very large. Solution that uses too much extra space will not pass the judge.

     Example:

     int[] nums = new int[] {1,2,3,3,3};
     Solution solution = new Solution(nums);

     // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
     solution.pick(3);

     // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
     solution.pick(1);

     time : O(n);
     * @param nums
     */
/*
 At first, let's get clear that count is used to count the target number in nums. 
 Say we now we have nums=[1,5,5,6,5,7,9,0] and the target is 5.

Now let's focus on the loop. When i=1, we get the first target number, and by rnd.nextInt(++count) 
we select a random number between [0, 1), which means actually you could only select 0, so the probability 
of making result = 1 is 1.

Keep going. In the loop where i = 2, we get the second number. Now we have to get a random number in {0,1}. 
So what should we do if we want to keep result = 1? It's simple: we have to make sure that, at this time, 
the random number generated should be 1 rather than 0 (otherwise the value of result will be changed), 
so the probability of keeping result = 1 is 1 * 1/2.

It is similar when we get the third target number, i.e., i = 4. Now we have to get a random number 
in {0,1,2}. If we still wish to keep result = 1, the only way is to randomly get number 1 or 2 
rather than 0, and the probability is 2/3. So the total probability of keeping result = 1 will be 1 * 1/2 * 2/3.

Since we have four target number 5 here, the final probability of keeping result = 1 would be 
1 * 1/2 * 2/3 * 3/4 = 1/4, which means the probability of picking index 0 is 1/4 as the problem 
required. The probability is the same if you wish to pick another index.

You may ask what is the probability of picking the last possible index 4? Well, it simpler. 
You may ignore all operations before the loop where i = 4, and the only thing you have to do is 
to get the random number 0 among {0,1,2,3} in the loop where i = 4, so the probability is exactly 1/4.
 */
    private int[] nums;
    Random rmd;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        rmd = new Random();
    }

    public int pick(int target) {
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }
         // this must be called found the value, 
            //The nextInt(int n) is used to get a random number between 0(inclusive) and the number passed 
            //in this argument(n),               
            // exclusive.
            
            // this is the key, this ways:
            // suppose there are 3 5 in the array, and when we meet first 5, the probobality is 100% 
            // but that's ok because later we are able to override, next 5, we have 50%, third we have 33%, 
            if (rmd.nextInt(++count) == 0) {
                res = i;
            }
        }
        return res;
    }
    
 /*假设:
输入：一组数据，大小未知

输出：这组数据的K个均匀抽取

要求：仅扫描一次

总体要求：从N个元素中随机的抽取k个元素，其中N无法确定，保证每个元素抽到的概率相同

  1）当n<=k时(总体数目 比 K要小，每一个都会被抽中)，出现在pool中的每个元素概率都是相同的，都为1 
    2）当n=k+1时,计算前k个元素在pool的概率 
    ==a==.前k个元素在pool中的元素概率都为1 
    ==b==.由假设得，第k+1个元素被选中的概率为：k/(k+1)，pool任意元素被替换的概率为(k/(k+1))*(1/k)=1/(k+1)，
    没被替换(即选中)的概率为1-1/(k+1)=k/(k+1). 
    由a*b=1*k/(k+1)=k/(k+1),前k个元素和k+1元素被选中的概率都为k/k+1。

    3）当n>k+1时，计算前n-1个元素在pool的概率 
    ==a==.前n-1个元素在pool中被选择的的概率为k/(n-1) 
    ==b==.由假设得,第n个元素被选中的概率为：k/n，pool任意元素被替换的概率为(k/n)*(1/k)=1/n，
    没被替换(即选中)的概率为1-1/n=(n-1/)n。 
    由a*b=(k/(n-1))*((n-1)/n)=k/n,前k个元素和k+1元素被选中的概率都为k/n。
  */
    // this is method for reservior sampling methods, randomly select k from large n
    static void selectKItems(int stream[], int n, int k) {
        int i;   // index for elements in stream[]
         
        // reservoir[] is the output array. Initialize it with
        // first k elements from stream[]
        int reservoir[] = new int[k];
        for (i = 0; i < k; i++)
            reservoir[i] = stream[i];
         
        Random r = new Random();
         
        // Iterate from the (k+1)th element to nth element
        for (; i < n; i++) {
            // Pick a random index from 0 to i.
            int j = r.nextInt(i + 1);
             
            // If the randomly  picked index is smaller than k,
            // then replace the element present at the index
            // with new element from stream
            if(j < k)
                reservoir[j] = stream[i];
        }
         
        System.out.println("Following are k randomly selected items");
        System.out.println(Arrays.toString(reservoir));
    }
     
}



