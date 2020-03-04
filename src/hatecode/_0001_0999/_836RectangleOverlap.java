package hatecode._0001_0999;
public class _836RectangleOverlap {
    /*
     * 836. Rectangle Overlap 
     * 
     * A rectangle is represented as a list [x1, y1, x2, y2],
     * where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2)
     * are the coordinates of its top-right corner. Example 1:
     * 
     * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3] Output: true
     */
  
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        if (rec1[0] > rec2[0]) {
            int[] temp = rec1;
            rec1 = rec2;
            rec2 = temp;
        }
        
        if (rec1[2] <= rec2[0]) return false;
        else if (rec1[1] <= rec2[1] && rec2[1] < rec1[3] 
                 || rec1[1] < rec2[3] && rec2[1] <= rec1[3] ) return true;
        
        return false;
    }
    
    public boolean isRectangleOverlap3(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
    
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) && // width > 0
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));  // height > 0
    }
/*
    Just from experience with these types of problems, it's usually easier to find out the opposite of what doesn't overlap. The following cases they don't overlap:
Case 1:if the maximum of rec1 is less than or equal to the minimum from rec2 in the x
Case 2:if the maximum of rec1 is less than or equal to the minimum from rec2 in the y
Case 3: just swap rec1 and rec2 in Case 1
Case 4:Just swap rec1 and rec2 in Case 2
All other cases they overlap.
*/
    //interview frinendly
    public boolean isRectangleOverlap4(int[] rec1, int[] rec2) {
        return(!(rec1[2]<=rec2[0]||rec1[3]<=rec2[1]||rec2[2]<=rec1[0]||rec2[3]<=rec1[1]));
    }
}