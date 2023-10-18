package companies;
import java.util.*;

public class MetaOnsite {
    

    public static boolean isMagic(int A) {
        Set<Integer> visited = new HashSet<>();
        
        visited.add(A);
        int cur = A;

        while(cur != 1) {
            int temp =helper(cur);
            if (visited.contains(temp)) return false;
            visited.add(temp);
            cur = temp;
        }


        return true;
    }


    private static int helper(int A) {
        int sum = 0;
        while (A > 0) {
            int temp = A%10;
            sum += temp * temp;
            A /=10;
        }

        return sum;
    }


    public static void main(String[] args) {
        System.out.println(isMagic(10));
        System.out.println(isMagic(12));
         System.out.println(isMagic(68));
    }
}
