package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BullsandCows
 * Creator : professorX
 * Date : Dec, 2017
 * Description : 299. Bulls and Cows
 */
public class _299BullsandCows {
    /**
     * For example:
     * 
     * Secret number: "1807" Friend's guess: "7810" Hint: 1 bull and 3 cows. (The
     * bull is 8, the cows are 0, 1 and 7.) Write a function to return a hint
     * according to the secret number and friend's guess, use A to indicate the
     * bulls and B to indicate the cows. In the above example, your function should
     * return "1A3B".
     * 
     * Secret number: "1123" Friend's guess: "0111" In this case, the 1st 1 in
     * friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should
     * return "1A1B".
     * 
     * time : O(n) space : O(1)
     * 
     * 
     * @param secret
     * @param guess
     * @return
     */

    //thinking process: 
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                // secret = "1123", guess = "0111",
                // count[1] ++--> 1
                // count[0] -- --> -1
                // count[2] ++ --> 1
                // count[1] -- --> 0 this time cow++, because previous it was 1
                // count[3] ++ ---> 1
                // count[1] -- ---> - 1
                // this templates is to have a storage which store the content it visited
                // we use array index to store the number we want to visit,
                // ++ means we visited in secret and -- means we visited in guess, then
                // if s count < 0 which means it was -- by guess branches.
                if (count[secret.charAt(i) - '0']++ < 0) cows++;
                if (count[guess.charAt(i) - '0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
