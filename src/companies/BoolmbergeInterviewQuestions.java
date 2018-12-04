package companies;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BoolmbergeInterviewQuestions {
    //this is for bloomberge phone interview
    public static String printNumbers(int n) {
        if (n== 0) return "0:1";
        n = Math.abs(n);
        Map<Integer, Integer> map = new HashMap<>();
        while(n >0) {
            map.put(n % 10, map.getOrDefault(n%10, 0) + 1);
            n = n/10;
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            sb.append(entry.getKey() + ":" + entry.getValue() + " ");
        }
        sb.deleteCharAt(sb.length() -1);
        return sb.toString();
    }
    //phone interview count words length = 3
    public static int countWords(String word) {
        if (word == null || word.length() < 3) {
            return 0;
        }
        int res = 0;
        for(int i = 0; i< word.length();) {
            if (word.charAt(i) == ' ') {
                i++;
                continue;
            }
            int cur = i;
            while(cur < word.length() && word.charAt(cur) != ' ') {
                cur++;
            }
            if (cur - i ==3) {
                res++;
            } 
            i = cur;
        }
        return res;
    }
    //给一个整数 求其二进制的第一个非零位的坐标
    public static int getCoordination(int n) {
        int count = 0;
        while ((n&1) == 0) {
            n = n >> 1;
            count++;
        }
        return 1 << count;
    }
    public static int getCoordination2(int n) {
       return n & (~(n-1));
    }
    
    
    //给一个array，求有多少个元素在一个range里。比如{1,3,2,7,5,9,0,0,2}, query (0,2), 
    //返回5。用的排序然后两次binary search
    
    //so if only called 1 time, then just visit,O(n),if we want long term benifit, then we 
    //sort and BS
    public static int getCountInRange(int[] nums, int start, int end) {
        if (nums == null || nums.length < 1 || start > end) {
            return 0;
        }
        Arrays.sort(nums);
        System.out.println("right-" + BSFromRight(nums, end) + " left:" + BSFromLeft(nums, start));
        return BSFromRight(nums, end) - BSFromLeft(nums, start) + 1;
    }
    
    public static int BSFromLeft(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + ((end - start)>>1);
            //since we want to move left so we put end to this place and ignore the big part
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] >= target) return start;
        else return end;
    }
    
    public static int BSFromRight(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + ((end - start)>>1);
            //we want to get most right, so we want to move start to right
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] <= target) return end;
        else return start;
    }
    
    //implement a ArrayList
    // do we 
    
    
    //rate limit, return true if access 3 times in last 3 seconds. 
    //follow up: if to add clientId. for each one
    long lastAccessTimeStamp = 0;
    int count = 0;
    public boolean isAboveThreshold() {
        if (count == 0) {
            lastAccessTimeStamp = new Date().getTime();
            count++;
            return false;
        }
        
        long elapsedTime = new Date().getTime() - lastAccessTimeStamp;
        if (elapsedTime > 3*1000) {
            count = 0;
            lastAccessTimeStamp = new Date().getTime();
        } else {
            count +=1;
            if (count == 3) {
                lastAccessTimeStamp = new Date().getTime();
                count = 0;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(printNumbers(-23330));
        System.out.println(countWords(" abc f def uuidfd ew    xcr"));
        System.out.println(getCoordination(8));
        System.out.println(getCoordination2(8));
        int[] test = {1,3,2,7,5,9,0,0,2};
        System.out.println(getCountInRange(test, 0,2));
    }
}
