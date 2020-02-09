package hatecode._0001_0999;
import java.util.*; 

/* Game Description- 
"A game is played between two players and there are N piles 
of stones such that each pile has certain number of stones. 
On his/her turn, a player selects a pile and can take any 
non-zero number of stones upto 3 (i.e- 1,2,3) 
The player who cannot move is considered to lose the game 
(i.e., one who take the last stone is the winner). 
Can you find which player wins the game if both players play 
optimally (they don't make any mistake)? " 

A Dynamic Programming approach to calculate Grundy Number 
and Mex and find the Winner using Sprague - Grundy Theorem. */

class GameGrundy { 
	

/* piles[] -> Array having the initial count of stones/coins 
			in each piles before the game has started. 
n	 -> Number of piles 

Grundy[] -> Array having the Grundy Number corresponding to 
			the initial position of each piles in the game 

The piles[] and Grundy[] are having 0-based indexing*/

static int PLAYER1 = 1; 
static int PLAYER2 = 2; 

// A Function to calculate Mex of all the values in that set 
    static int calculateMex(HashSet<Integer> Set) {
        int Mex = 0;

        while (Set.contains(Mex)) Mex++;

        return Mex;
    }

    // A function to Compute Grundy Number of 'n'
    static int calculateGrundy(int n, int grundy[]) {
        grundy[0] = 0;
        grundy[1] = 1;
        grundy[2] = 2;
        grundy[3] = 3;

        if (grundy[n] != -1) return (grundy[n]);

        // A Hash Table
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 1; i <= 3; i++)
            set.add(calculateGrundy(n - i, grundy));

        // Store the result
        grundy[n] = calculateMex(set);

        return grundy[n];
    }

    // A function to declare the winner of the game
    static void declareWinner(int whoseTurn, int piles[], int Grundy[], int n) {
        int xorValue = Grundy[piles[0]];

        for (int i = 1; i <= n - 1; i++)
            xorValue = xorValue ^ Grundy[piles[i]];

        if (xorValue != 0) {
            if (whoseTurn == PLAYER1)
                System.out.printf("Player 1 will win\n");
            else
                System.out.printf("Player 2 will win\n");
        } else {
            if (whoseTurn == PLAYER1)
                System.out.printf("Player 2 will win\n");
            else
                System.out.printf("Player 1 will win\n");
        }

        return;
    }

    // Driver code
    public static void main(String[] args) {

        // Test Case 1
        int piles[] = {
                3, 4, 5 };
        int n = piles.length;

        // Find the maximum element
        int maximum = Arrays.stream(piles).max().getAsInt();

        // An array to cache the sub-problems so that
        // re-computation of same sub-problems is avoided
        int Grundy[] = new int[maximum + 1];
        Arrays.fill(Grundy, -1);

        // Calculate Grundy Value of piles[i] and store it
        for (int i = 0; i < n ; i++)
            calculateGrundy(piles[i], Grundy);

        declareWinner(PLAYER1, piles, Grundy, n);

        /*
         * Test Case 2 int piles[] = {3, 8, 2}; int n = sizeof(piles)/sizeof(piles[0]);
         * 
         * 
         * int maximum = *max_element (piles, piles + n);
         * 
         * // An array to cache the sub-problems so that // re-computation of same
         * sub-problems is avoided int Grundy [maximum + 1]; memset(Grundy, -1, sizeof
         * (Grundy));
         * 
         * // Calculate Grundy Value of piles[i] and store it for (int i=0; i<=n-1; i++)
         * calculateGrundy(piles[i], Grundy);
         * 
         * declareWinner(PLAYER2, piles, Grundy, n);
         */

    }
}