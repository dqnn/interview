package hatecode._0001_0999;

public class GoogleFindExtreaCharacter {
/*
 * From Google interview doc,not from LC
 * 
 * Problem statements:
 * string s1 s2, s2会比s1多一个char，
要你找出来那个char的index 比如 today, todxay, return 3
 */
    //BS, if 
    public int findExtra(String s1, String s2) {
        if(s1.length() == 0) return 0;
        if(s2.length() == 0) return 0;
        String longer = s1.length() > s2.length() ? s1 : s2;
        String shorter = s1.length() > s2.length() ? s2 : s1;
        int l = 0, r = longer.length() - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(longer.charAt(mid) == shorter.charAt(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if(longer.charAt(l) != shorter.charAt(l)) return l;
        else return r;
   }

}
