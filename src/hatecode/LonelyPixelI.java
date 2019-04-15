package hatecode;
class LonelyPixelI {
/*
531. Lonely Pixel I
Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3

*/
/*
W + 1 = X --> one item in the row/column
B + 1 = C --> one item in the row/column, and the first row is the black pixel
W + 2 = Y --> two items in the row/column
W - 1 = V --> this prevents wrap-around past W if there are more than 255 black pixels in a row/column
 */
    public int findLonelyPixel(char[][] m) {
        int r = m.length, c = m[0].length;

        int firstRowCount = 0;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (m[i][j] == 'B') {
                    if (m[0][j] < 'Y' && m[0][j] != 'V') m[0][j]++;
                    
                    if (i == 0) firstRowCount++;
                    else if (m[i][0] < 'Y' && m[i][0] != 'V') m[i][0]++;
                }

        int res = 0;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (m[i][j] < 'W' && (m[0][j] == 'C' || m[0][j] == 'X')) {
                    if (i == 0) res += firstRowCount == 1 ? 1 : 0;
                    else if (m[i][0] == 'C' || m[i][0] == 'X') res++;
                }
        return res;
    }

    //O(mn)/O(m+n)
    public int findLonelyPixel_BrutalForce(char[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) return 0;
        
        int r = m.length, c= m[0].length;
        int[] rows = new int[r];
        int[] cols = new int[c];
        for(int i = 0; i< r; i++) {
            for(int j = 0; j< c; j++) {
                if (m[i][j] == 'B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int res = 0;
        for(int i = 0; i< r; i++) {
            for(int j = 0; j< c; j++) {
                if (m[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    res++;
                }
            }
        }
        
        return res;
    }
}