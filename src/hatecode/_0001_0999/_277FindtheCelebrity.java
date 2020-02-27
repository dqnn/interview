package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindtheCelebrity
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 277. Find the Celebrity
 */
public class _277FindtheCelebrity {
    /**
     * Suppose you are at a party with n people (labeled from 0 to n - 1) and among
     * them, there may exist one celebrity. The definition of a celebrity is that
     * 
     * all the other n - 1 people know him/her but he/she does not know any of them.
     * 
     * You are given a helper function bool knows(a, b) which tells you whether A
     * knows B. Implement a function int findCelebrity(n), your function should
     * minimize the number of calls to knows.
     *
     * 0 1 2 3 4 5 3 : Celebrity 0 : 1 1: 2 2 : 4 4 : 5 5 : 1 possible = 3
     *
     * time : O(n) space : O(1)
     *
     * @param n
     * @return
     */
    //thinking process: 
    //like 0->1->2->3->4->8->5->6 but 6 knows 0, after first loop, celebrity = 6
    //
    public int findCelebrity(int n) {
        if (n < 2) return -1;
        int celebrity = 0;
        //so after this loop, 0-celebrity-1 and celebrity+1..n-1 will not be celebrity
        //the possible is only chance to be celebrity
        //but we not sure celebirty is true becasue this loop also ignore a lot info, like 
        //when we compare, some number may not know celebrity but we missed
        
        //this is a tricky loop, i  will replace pre i, so last one is definitely highest probability 
        //celebrity, so next step is to verify this, because this loop just provide the highest probability
        //candidate
        for (int i = 1; i < n; i++) {
            if (!knows(i, celebrity)) {
                celebrity = i;
            }
        }
        //this loop is to verify this celebrity, if he know someone or 
        //someone does not know him then he is not
        for (int i = 0; i < n; i++) {
            if (i == celebrity) continue;
            //if i does not know candidate, or candidate knows i, return -1;
            if (knows(celebrity, i) || !knows(i, celebrity)) {
                return -1;
            }
        }
        return celebrity;
    }

    public boolean knows(int a, int b) {
        return true;
    }
}
