package hatecode._0001_0999;
public class DeleteColumnsToMakeSorted {
/*
944. Delete Columns to Make Sorted
Example 1:

Input: ["cba","daf","ghi"]
Output: 1
Explanation: 
After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
Example 2:

Input: ["a","b"]
Output: 0
Explanation: D = {}
*/
    public int minDeletionSize(String[] A) {
        int count = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j-1].charAt(i) > A[j].charAt(i)) {
                    count++;
                    break;
                }
            }
         }
        
        return count;
    }
}