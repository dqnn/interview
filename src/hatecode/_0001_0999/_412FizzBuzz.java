package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by professorX on 28/07/2017.
 */
public class _412FizzBuzz {
    /**
     * 412. Fizz Buzz
     * Write a program that outputs the string representation of numbers from 1 to n.

     But for multiples of three it should output “Fizz” instead of the number and for the multiples of five
     output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

     n = 15,

     Return:
     [
     "1",
     "2",
     "Fizz",
     "4",
     "Buzz",
     "Fizz",
     "7",
     "8",
     "Fizz",
     "Buzz",
     "11",
     "Fizz",
     "13",
     "14",
     "FizzBuzz"
     ]

     time : O(n);
     space : O(n);

     * @param n
     * @return
     */
    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
    
    // here is more complicated condition but as we can see 
    // the condition should be like from more restricted then to more open
    // and easy
    public List<String> fizzBuzz2(int n) {
        List<String> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }
        
        for(int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                res.add("Fizz");
            } else if (i % 3 != 0 && i % 5 == 0) {
                res.add("Buzz");
            } else if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
}
