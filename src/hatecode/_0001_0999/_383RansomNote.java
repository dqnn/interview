package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RansomNote
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 383. Ransom Note
 */
public class _383RansomNote {
    /**
     * You may assume that both strings contain only lowercase letters.

     canConstruct("a", "b") -> false
     canConstruct("aa", "ab") -> false
     canConstruct("aa", "aab") -> true

     time : O(n)
     space : O(1)

     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if ( ransomNote == null || magazine == null) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--count[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
