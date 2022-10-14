package hatecode._0001_0999;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Description : 179. Largest Number
 */
public class _179LargestNumber {
    /**
     * Given a list of non negative integers, arrange them such that they form the largest number.

     For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

     Note: The result may be very large, so you need to return a string instead of an integer.

     time : O(nlogn)
     space : O(n)

     * @param A
     * @return
     */
    public String largestNumber(int[] A) {
        if (A == null || A.length == 0) {
            return "";
        }
        String[] res = new String[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = String.valueOf(A[i]);
        }
        Arrays.sort(res, new Comparator<String>(){
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1);
            }
        });
        if (res[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : res) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    // we using PriorityQueue to do heap sort, O(nlogn) space O(n)
    public String largestNumber2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return "";
        }
        //(a,b)-->(a-b) means smaller is before bigger one, ascend sort
        Queue<String> q = new PriorityQueue<>((a, b) -> ((b + a).compareTo(a + b)));

        for (int temp : nums) {
            q.offer(String.valueOf(temp));
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            sb.append(q.poll());
        }

        String res = sb.toString();
        return res.startsWith("0") ? "0" : res;
    }
    
    public String largestNumber3(int[] nums) {
        return IntStream.range(0, nums.length).mapToObj(i -> "" + nums[i])
                // we cannot use x.compareTo(y) this use case [3,30,34,5,9]
                // 9 5 34 30 3, actually correct is 9 5 34 3 30
            .sorted((x, y) -> (y + x).compareTo(x + y))
            .reduce("0", (x1, x2) -> x1.equals("0") ? x2 : x1 + x2);
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {3,30,34,5,9};
        Arrays.stream(nums)
        .mapToObj(e -> String.valueOf(e))
        .sorted((a, b)->(b.compareTo(a))).forEach(System.out::print);
    }
}
