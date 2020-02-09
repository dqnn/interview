    package hatecode._0001_0999;

import java.util.Arrays;
import java.util.Stack;

public class FindTheShortestSuperstring {
/*
 * 943. Find the Shortest Superstring
Travelling Salesman Problem

Given an array A of strings, find any smallest string that contains 
each string in A as a substring.

We may assume that no string in A is substring of another string in A.

 
Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"
 

Note:

1 <= A.length <= 12
1 <= A[i].length <= 20
 */
    /*
distance calc rule:
if A.equals(B) == true, then it is A.length() or B .length()
else we would choose B.length() - A.length() + common part length
         ["catg","ctaagt","gcta","ttca","atgcatc"]
  catg    [[4,       6,      3,     4,      4], 
  ctaagt   [4,       6,      4,     3,      7], 
  gcta     [4,       3,      4,     4,      6], 
  ttca     [2,       6,      4,     4,      6], 
  atgcatc  [3,       5,      4,     4,      7]]
 
for DP matrix, we process only i[1,31] && (1 << j[0,5))) > 0 
which means are i = 1,2,4,8,16
others we skip until i = 31, we use min to track the min cost.
and we update last = j;

Path here means 
 
Time complexity: O(n^2 * (2^n + W)) W is max of each word
     */
/*
graph[i][j] means the length of string to append when A[i] followed by A[j] then A[i]
can be super string for A[i] and A[j]
eg. A[i] = abcd, A[j] = bcde, then graph[i][j] = 1
Then the problem becomes to: find the shortest path in this graph which 
visits every node exactly once. This is a Travelling Salesman Problem.
Apply TSP DP solution. Remember to record the path.

Another explanation of TSP:
其中的 d(i,{j,k,l}) 表示由城市 i 出发, 集合 {j,k,l} 中的城市 j,k,l 每个经过一次需要的最小路程, 箭头上的值表示两个城市之间的距离. 
很显然, d(0,{1,2,3}) 就是我们最终要求的值. 这个值可以一步一步分解下去, 最终分解为每个城市到城市0的距离, 
d(i,{}). 将过程反过来, 向上递推, 就可以得到需要的值了.

为了方便求解并记录路径, 我们可以使用二进制数表示城市集合. 一个 n 位的二进制数可以表示 n 个城市的集合. 
当某位为1时, 表示这个位所代表的城市出现在集合中. 我们最多有3个城市需要经历, 所以需要 2<<3=8 个集合. 
每个集合对应的数字如下:
编号  二进制 集合
0   000 {}
1   001 {1}
2   010 {2}
3   011 {1,2}
4   100 {3}
5   101 {1,3}
6   110 {2,3}
7   111 {1,2,3}
在上面图中, d[i,j] 中的 j 使用的就是表中对应的集合. 根据递推关系

d[i,j]= Ci0, j = 0, 
        min{Cik+d[k,j∖{k}]}=min{Cik+d[k,j−2<<(k−1)]},j=0j≠0
其中的 j∖{k} 表示从集合 j 中去除集合 {k}. 
集合 j 去除 城市 k 后所形成的集合, 
其对应的编号为 j−2<<(k−1). 判断城市 k 是否属于集合 
j 的方法是使用二进制与操作, 若j & 2^(k-1) == 0, k 不属于集合 j.

在写代码实现时, 我们可以使用一个二维表格(数组), 其大小为 n2n−1, 
根据上面的关系式逐次填充计算值, 最终得到结果.

 */
    public static String shortestSuperstring(String[] A) {
        int inStrArrLen = A.length;
        int[][] graph = new int[inStrArrLen][inStrArrLen];
        // build the graph, "catg" 0  -> "ctaagt" 1, common substring's length 
        //g[0][1] = 6,g[0][1] = 4
        for (int i = 0; i < inStrArrLen; i++) {
            for (int j = 0; j < inStrArrLen; j++) {
                graph[i][j] = calc(A[i], A[j]);
                graph[j][i] = calc(A[j], A[i]);
            }
        }
        //1 << n means Math.pow(2, 5) = 32
        int mLen = 1 << inStrArrLen;
        int[][] dp = new int[mLen][inStrArrLen];
        //2D matrix to show from ""
        int[][] path = new int[mLen][inStrArrLen];
        int last = -1, min = Integer.MAX_VALUE;
        
        //why we did not initialize dp and path
        // start TSP DP, from 1 to pow(2,5) - 1, permutations
        for (int i = 1; i < mLen; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            //j belong to [0,4], j stands for one city, i stands set of cities
            //so we use i & (1 << j) == 0 to detect whether j was in i or not, if true then no vice yes. 
            for (int j = 0; j < inStrArrLen; j++) {
                // i means the power of 2,
                int tempJ = 1 << j;
                // i & (1<< j) menas j belong to i set 
                if ((i & tempJ) > 0) {
                   System.out.println("i-j-tempJ:" + i + "-" + j + "-" +tempJ);
                    //prev here means the cities set without j, notes, i>=1 and j starts from 0 so 
                    int prev = i - tempJ;
                    //means empty set, 
                    if (prev == 0) {
                        //empty to one city means the follower's length
                        dp[i][j] = A[j].length();
                     //pre did not contain j
                    } else {
                        //we use prev to connect each city, plus graph weight
                        for (int k = 0; k < inStrArrLen; k++) {
                            //prev->k->j
                            System.out.println(String.format("prev:%s-k:%s-j:%s", 
                                    prev,k,j));
                            int newCost = dp[prev][k] + graph[k][j];
                            if (dp[prev][k] < Integer.MAX_VALUE 
                                    && newCost < dp[i][j]) {
                                dp[i][j] = newCost;
                                path[i][j] = k;
                            }
                        }
                    }
                }
                if (i == mLen - 1 && dp[i][j] < min) {
                    min = dp[i][j];
                    last = j;
                }
            }
        }
		
        // build the path
        StringBuilder sb = new StringBuilder();
        int cur = mLen - 1;
        Stack<Integer> stack = new Stack<>();
        while (cur > 0) {
            stack.push(last);
            int temp = cur;
            cur -= (1 << last);
            last = path[temp][last];
        }

        // build the result
        int i = stack.pop();
        sb.append(A[i]);
        while (!stack.isEmpty()) {
            int j = stack.pop();
            sb.append(A[j].substring(A[j].length() - graph[i][j]));
            i = j;
        }
        return sb.toString();
    }
    static int calc(String a, String b) {
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return b.length() - a.length() + i;
            }
        }
        return b.length();
    }
    public static void main(String[] args) {
        String[] input = new String[5];
        input[0] = "catg";
        input[1] = "ctaagt";
        input[2] = "gcta";
        input[3] = "ttca";
        input[4] = "atgcatc";
        
        System.out.print(shortestSuperstring(input));
    }
/*
Intuition

We have to put the words into a row, where each word may overlap the previous word. 
This is because no word is contained in any word.

Also, it is sufficient to try to maximize the total overlap of the words.

Say we have put some words down in our row, ending with word A[i]. Now say we put down 
word A[j] as the next word, where word j hasn't been put down yet. 
The overlap increases by overlap(A[i], A[j]).

We can use dynamic programming to leverage this recursion. 
Let dp(mask, i) be the total overlap after putting some words 
down (represented by a bitmask mask), for which A[i] was the last word put down. 
Then, the key recursion is dp(mask ^ (1<<j), j) = max(overlap(A[i], A[j]) + dp(mask, i)),
 where the jth bit is not set in mask, and i ranges over all bits set in mask.

Of course, this only tells us what the maximum overlap is for each set of words. 
We also need to remember each choice along the way (ie. the specific i that made 
dp(mask ^ (1<<j), j) achieve a minimum) so that we can reconstruct the answer.

Algorithm

Our algorithm has 3 main components:

Precompute overlap(A[i], A[j]) for all possible i, j.
Calculate dp[mask][i], keeping track of the "parent" i for each j as described above.
Reconstruct the answer using parent information.
Time Complexity: O(N^2 (2^N + W)) where NN is the number of words, 
and WW is the maximum length of each word.

Space Complexity: O(N (2^N + W))
 */
    public String shortestSuperstring2(String[] A) {
        int N = A.length;

        // Populate overlaps
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    int m = Math.min(A[i].length(), A[j].length());
                    for (int k = m; k >= 0; --k)
                        if (A[i].endsWith(A[j].substring(0, k))) {
                            overlaps[i][j] = k;
                            break;
                        }
                }
            }

        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1<<N][N];
        int[][] parent = new int[1<<N][N];
        for (int mask = 0; mask < (1<<N); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit) if (((mask >> bit) & 1) > 0) {
                // Let's try to find dp[mask][bit].  Previously, we had
                // a collection of items represented by pmask.
                int pmask = mask ^ (1 << bit);
                if (pmask == 0) continue;
                for (int i = 0; i < N; ++i) if (((pmask >> i) & 1) > 0) {
                    // For each bit i in pmask, calculate the value
                    // if we ended with word i, then added word 'bit'.
                    int val = dp[pmask][i] + overlaps[i][bit];
                    if (val > dp[mask][bit]) {
                        dp[mask][bit] = val;
                        parent[mask][bit] = i;
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j)
            if (dp[(1<<N) - 1][j] > dp[(1<<N) - 1][p])
                p = j;

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t/2; ++i) {
            int v = perm[i];
            perm[i] = perm[t-1-i];
            perm[t-1-i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < N; ++i) if (!seen[i])
            perm[t++] = i;

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(A[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i-1]][perm[i]];
            ans.append(A[perm[i]].substring(overlap));
        }

        return ans.toString();
    }
}