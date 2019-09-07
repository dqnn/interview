package hatecode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AssignCookies
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 455. Assign Cookies
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give 
 * each child at most one cookie. Each child i has a greed factor gi, 
 * which is the minimum size of a cookie that the child will be content with; 
 * and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, 
 * and the child i will be content. Your goal is to maximize the number of your content 
 * children and output the maximum number.

Note:
You may assume the greed factor is always positive. 
You cannot assign more than one cookie to one child.
 */
public class _455AssignCookies {
    /**
     * Example 1:
     Input: [1,2,3], [1,1]

     Output: 1

     Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
     And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
     You need to output 1.
     Example 2:
     Input: [1,2], [1,2,3]

     Output: 2

     Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
     You have 3 cookies and their sizes are big enough to gratify all of the children,
     You need to output 2.

     time : O(nlogn)
     space : O(1)

     */
    
    /*
    time: O(ngln)
    space O(1)
   */
	// this is more diffciluty than orignial question, since we consider we can split the cookie
   public int findContentChildren2(int[] g, int[] s) {
       //edge case
       if (g == null || s == null || g.length < 1 || s.length < 1) {
           return 0;
       }
       
       Arrays.sort(g);
       Arrays.sort(s);
       int cnt = 0, left = 0;
       int i = 0, j = 0;
       while(i < s.length && j < g.length) {
           if (left + s[i] >= g[j]) {
               cnt ++;
               left += s[i] - g[j];
               i++;
               j++;
           } else {
               left += s[i];
               i++;
           }
       }
       
       return cnt;
   }
   
   /*
    time: O(ngln)
    space O(1)
   */
   public int findContentChildren3(int[] g, int[] s) {
       //edge case
       if (g == null || s == null || g.length < 1 || s.length < 1) {
           return 0;
       }
       
       Arrays.sort(g);
       Arrays.sort(s);
       int cnt = 0;
       int i = 0, j = 0;
       while(i < s.length && j < g.length) {
           if (s[i] >= g[j]) {
               cnt ++;
               i++;
               j++;
           } else {
               i++;
           }
       }
       
       return cnt;
   }
}
