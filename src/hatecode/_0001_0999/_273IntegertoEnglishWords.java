package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IntegertoEnglishWords
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 273. Integer to English Words
 */
public class _273IntegertoEnglishWords {
    /**
     * Convert a non-negative integer to its english words representation. 
     * Given input is guaranteed to be less than 2^31 - 1.

     For example,
     123 -> "One Hundred Twenty Three"
     12345 -> "Twelve Thousand Three Hundred Forty Five"
     1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

     time : O(n) / O(1)
     space : O(1)


     */
    //best and interview friendly
    //so the solution is carefully insights on how the result string formed. 
    //thousand is the threshold, it setup 3 arrays to form the low level things
    
    //the trick needs to be remembered
    //example, 12345->"Twelve Thousand Three Hundred Forty Five"
    //Twelve|  Thousand | Three | Hundred | Forty | Five
    
    //first we break the number by 1000, then we will have above 1000 and below 1000 part
    //why we "" at first because if it is 20 like, it is less than 1000, so we should not follow 
    //thousands, so we use "" to skip it
    static String[] less20={"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", 
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", 
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] tens={"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", 
            "Seventy", "Eighty", "Ninety"};
    static String[] thousands={"", "Thousand", "Million", "Billion"};

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";
        String res = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                // i = 0 it is ""
                res = helper(num % 1000) + thousands[i] + " " + res;
            }
            num /= 1000;
            i++;
        }
        //do not forget trim(), we need to remove some spaces we added in coding
        return res.trim();
    }
    public static String helper(int num) {
        if (num == 0) return "";
        if (num < 20) {
            //leave a space for concatting
            return less20[num % 20] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + helper(num % 10);
        } else {
            //for above 100, it has same character like less20, 
            return less20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
    
    
    // another easy understand solutions
    private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords2(int num) {
        if (num == 0) return "Zero";
        return helper2(num); 
    }
    
    private String helper2(int num) {
        String result = new String();
        if (num < 10) result = belowTen[num];
        else if (num < 20) result = belowTwenty[num -10];
        else if (num < 100) result = belowHundred[num/10] + " " + helper2(num % 10);
        else if (num < 1000) result = helper2(num/100) + " Hundred " +  helper2(num % 100);
        else if (num < 1000000) result = helper2(num/1000) + " Thousand " +  helper2(num % 1000);
        else if (num < 1000000000) result = helper2(num/1000000) + " Million " +  helper2(num % 1000000);
        else result = helper2(num/1000000000) + " Billion " + helper2(num % 1000000000);
        return result.trim();
    }
    
    public static void main(String[] args) {
        System.out.println(numberToWords(12));
    }
}
