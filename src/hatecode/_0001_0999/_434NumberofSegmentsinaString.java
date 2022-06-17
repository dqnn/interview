package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofSegmentsinaString
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 434. Number of Segments in a String
 */
public class _434NumberofSegmentsinaString {
    /**
     * Count the number of segments in a string, where a segment is defined to be a contiguous sequence 
     * of non-space characters.

     Please note that the string does not contain any non-printable characters.

     Example:

     Input: "Hello, my name is John"
     Output: 5

     time : O(n)
     space : O(1)

     * @param s
     * @return
     */
    public int countSegments(String s) {
        if (s == null) {
            return 0;
        }
        
        s = s.trim();
        
        if (s.length() < 1) {
            return 0;
        }
        return s.split("\\s+").length;
    }
}
