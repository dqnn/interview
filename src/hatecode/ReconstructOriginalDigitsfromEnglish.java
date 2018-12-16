package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReconstructOriginalDigitsfromEnglish
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 423. Reconstruct Original Digits from English
 */
public class ReconstructOriginalDigitsfromEnglish {
    /**
     * Given a non-empty string containing an out-of-order English representation of digits 0-9,
     * output the digits in ascending order.

     Note:
     Input contains only lowercase English letters.
     Input is guaranteed to be valid and can be transformed to its original digits.
     That means invalid inputs such as "abc" or "zerone" are not permitted.
     Input length is less than 50,000.
     Example 1:
     Input: "owoztneoer"

     Output: "012"

     zero one two three four five six seven eight nine

     char c = s.charAt(i)

     s(6) + s(7) = 5
     s(6) = 2 x = 2

     time : O(n)
     space : O(n)

     * @param s
     * @return
     */
    /*
for zero, it's the only word has letter 'z',
for two, it's the only word has letter 'w',
......
so we only need to count the unique letter of each word, Coz the input is always valid.
     */
    //O(n)
    public String originalDigits(String s) {
        if (s == null || s.length() < 3) {
            return "";
        }
        int[] count = new int[10];
        for (char c : s.toCharArray()) {
            switch(c) {
                case 'z': count[0]++; break; // 0
                case 'w': count[2]++; break;// 2
                case 'x': count[6]++; break;// 6
                case 's': count[7]++; break;//7-6
                case 'g': count[8]++; break;// 8
                case 'u': count[4]++; break;// 4
                case 'f': count[5]++; break;//5-4
                case 'h': count[3]++; break;//3-8
                case 'i': count[9]++; break;//9-8-5-6
                case 'o': count[1]++; break;//1-0-2-4
                default: break;
            }
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];

        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < count[i]; j++) {
                res.append(i);
            }
        }
        return res.toString();
    }
}
