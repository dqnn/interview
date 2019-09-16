package hatecode;
public class _997FindTheTownJudge {
    /*
     * 997. Find the Town Judge 
     * In a town, there are N people labelled from 1 to N.
     * There is a rumor that one of these people is secretly the town judge.
     * 
     * If the town judge exists, then:
     * 
     * The town judge trusts nobody. Everybody (except for the town judge) trusts
     * the town judge. There is exactly one person that satisfies properties 1 and
     * 2. You are given trust, an array of pairs trust[i] = [a, b] representing that
     * the person labelled a trusts the person labelled b.
     * 
     * If the town judge exists and can be identified, return the label of the town
     * judge. Otherwise, return -1.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: N = 2, trust = [[1,2]] Output: 2
     */
    
    //thinking process:
    //the problem is to say: given list of arrays, from the graph, find 
    //the judge
    //so everyone trust the judge but judge did not trust anyone so from DAG perspective, 
    //so judge must be node which has most in degree and its degree must equal N - 1, if not means 
    //judge knows someone
    
    //it is quite similar compared to 227, Find the Celebrity
    //the difference: celebrity is harder because it didnot provide the trust array, it just has 
    //a API to tell who knows who, so we need to find the highest probability celebrity and then 
    //verify, but this one already provide full list so we can know more than 227
    public int findJudge(int N, int[][] t) {
        if (t == null || t.length < 1) return N;
        int[] cnt = new int[N + 1];
        for(int[] e: t) {
            cnt[e[0]]--;
            cnt[e[1]]++;
        }
        for(int i=1;i<=N; i++) if (cnt[i] == N-1) return i;
        return -1;
    }
}