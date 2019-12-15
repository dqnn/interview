package hatecode;
public class _1105FillingBookcaseShelves {
/*
1105. Filling Bookcase Shelves
We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
*/
    //thinking process:
    //given books array, books[i][0]--width, books[i][1]--height, given another
    //fixed width, we want to place a list of books on the shelf, row of books should 
    //not exceed the shelf_width, 
    //but when row of books width < shelf_width, we can place same row or next row, so return
    //the min height
    
    //the problem is to set one dimension, return another min/max dimension, knapsacks
    //we assume dp[i] = dp[i-1] + books[i-1][1], then from j = 1...i-1, we want to 
    //use book[i-1] to compare book[j], 
    //get max height
    //test height, whether we can place this book one same row
    //get min height if we put this book on this row
    public int minHeightShelves(int[][] books, int shelf_width) {
        //dp[i] means for 0-i-1 books, the min height we can place on the shelf
        int[] dp = new int[books.length + 1];
        dp[0] = 0;
        
        for (int i = 1; i <= books.length; ++i) {
            int w = books[i-1][0];
            int h = books[i-1][1];
            dp[i] = dp[i-1] + h;
            for (int j = i - 1; j > 0 && w + books[j-1][0] <= shelf_width; --j) {
                h = Math.max(h, books[j-1][1]);
                w += books[j-1][0];
                dp[i] = Math.min(dp[i], dp[j-1] + h);
            }
        }
        return dp[books.length];
    }
    
    //dfs solution,O(n*m)/O(n), if not memo, then O(2^n)/O(n)
    public int minHeightShelves_DFS(int[][] books, int sw) {
        Integer[] dp = new Integer[books.length];
        return helper(books, sw, 0, dp);
    }
    
    public int helper(int[][] books, int sw, int index, Integer[] dp){
        if(index >= books.length) return 0;
        
        if(dp[index] != null) return dp[index];
        
        int w = 0, h = 0;
        int res = Integer.MAX_VALUE;
        for(int i = index; i < books.length && (w+books[i][0] <= sw); i++){
            w += books[i][0];
            h = Math.max(h , books[i][1]);
            
            int r = helper(books, sw, i+1, dp);
            if(r != Integer.MAX_VALUE){
               res = Math.min(res, r+h);   
            }
        }
        
        dp[index] = res;
        return res;
    }
}