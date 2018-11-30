package leetcode;

import java.util.HashMap;

public class NumberPartialSumEqualsToAnotherHalf {

    // 把1 ~999分成27组
    public static int getAllcount() {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 100; i <= 999; i++) {
            int sum = getSum(i); //O(n)
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        int res = 0;
        for (int i = 1; i <= 999; i++) {
            int sum = getSum(i);
            if (map.containsKey(sum)) {
                res+= map.get(sum); 
            } 
        }
        return res;
   }

    private static int getSum(int k) {
        int sum = 0;
        while (k > 0) {
            sum = sum + k % 10;
            k = k / 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(getAllcount());
    }
}
