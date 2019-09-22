package hatecode;
public class _1025DivisorGame {
/*
1025. Divisor Game
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number N on the chalkboard.  
On each player's turn, that player makes a move consisting of:

Choosing any x with 0 < x < N and N % x == 0.
Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.

Example 1:

Input: 2
Output: true
*/
    //thinking process:
    //given a number N, two players, everyone will 
    // choose any number 0<x<N && N % x == 0, then N->N - x, 
    //return true if and only if first player wins
    
    //dp[i] means if alice move, true is win, false is lose
    //
    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n +1];
        dp[0] = false;
        dp[1] = false;
        for(int i = 2; i<=n; i++) {
            for(int j = 1; j<i;j++) {
                if (i % j == 0 && !dp[i -j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
/*If N is even.
We can choose x = 1.
The opponent will get N - 1, which is a odd.
Reduce to the case odd and he will lose.

If N is odd,
2.1 If N = 1, lose directly.
2.2 We have to choose an odd x.
The opponent will get N - x, which is a even.
Reduce to the case even and he will win.

 */
    public boolean divisorGame_Reference(int n) {
        return n % 2 == 0;
    }
}