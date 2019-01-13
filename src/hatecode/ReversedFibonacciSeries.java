package hatecode;

import java.util.*;

public class ReversedFibonacciSeries {

    public static void main(String[] args) {
        System.out.println(reverseFibo(80,50, 10));
        System.out.println(reverseFibo(-2,-5, 10));
    }
    
    //from example,80,50,30, 20,10,10,0, we can see res[n] = res[n-2]-res[n-1]
    //Time: O(n)  n is   
    //Space: O(n) 
    public static List<Integer> reverseFibo(int first, int sec, int n) {
        //result list
        List<Integer> res = new ArrayList<>();
        //add first 2 numbers as calculat base
        res.add(first);
        res.add(sec);
        if (n <=2) return res;
        
        n -= 2;
        //forumla
        int num = first - sec;
        //loop until 0 to get the result
        while(n > 0) {
            res.add(num);
            num = res.get(res.size() - 2) - res.get(res.size() - 1);
            n--;
        }
        return res;
    }

}
