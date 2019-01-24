package hatecode;
public class ChampagneTower {
    /*
     * 799. Champagne Tower 
     * We stack glasses in a pyramid, where the first row has 1
     * glass, the second row has 2 glasses, and so on until the 100th row. Each
     * glass holds one cup (250ml) of champagne.
     * 
     * Then, some champagne is poured in the first glass at the top. When the top
     * most glass is full, any excess liquid poured will fall equally to the glass
     * immediately to the left and right of it. When those glasses become full, any
     * excess champagne will fall equally to the left and right of those glasses,
     * and so on. (A glass at the bottom row has it's excess champagne fall on the
     * floor.)
     * 
     * For example, after one cup of champagne is poured, the top most glass is
     * full. After two cups of champagne are poured, the two glasses on the second
     * row are half full. After three cups of champagne are poured, those two cups
     * become full - there are 3 full glasses total now. After four cups of
     * champagne are poured, the third row has the middle glass half full, and the
     * two outside glasses are a quarter full, as pictured below.
     */
    //thinking process:
    //O(R^2)/O(R^2)
    //each row would have row + 1 cups, so res[0]
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] res = new double[101];
        res[0] = poured;
        for (int row = 1; row <= query_row; row++)
            //from row to 0, then 
            for (int i = row; i >= 0; i--) {
                res[i] = Math.max(0.0, (res[i] - 1) / 2);
                res[i + 1] += res[i];
            }

        return Math.min(res[query_glass], 1.0);
    }
    //A[r][c] means if A[r][c] is full, how many left for row below two cups
    //above is the space improvements for this one
    public double champagneTower2(int poured, int query_row, int query_glass) {
        double[][] A = new double[102][102];
        A[0][0] = (double) poured;
        for (int r = 0; r <= query_row; ++r) {
            for (int c = 0; c <= r; ++c) {
                double q = (A[r][c] - 1.0) / 2.0;
                if (q > 0) {
                    A[r + 1][c] += q;
                    A[r + 1][c + 1] += q;
                }
            }
        }
        return Math.min(1, A[query_row][query_glass]);
    }
    
    
}