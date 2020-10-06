package hatecode._1000_1999;
public class _1183MaximumNumberOfOnes {
/*
1183. Maximum Number of Ones
Consider a matrix M with dimensions width * height, such that every cell has value 0 or 1, and any square sub-matrix of M of size sideLength * sideLength has at most maxOnes ones.

Return the maximum possible number of ones that the matrix M can have.

 

Example 1:

Input: width = 3, height = 3, sideLength = 2, maxOnes = 1
Output: 4
*/
    //thinking process: O()/O()
    
    //the problem is to say: 
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int M = sideLength * sideLength;
        if (maxOnes >= M) return width * height;
        int[] res = new int[M];
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                res[i * sideLength + j] += ((width - i - 1) / sideLength + 1) * ((height - j - 1) / sideLength + 1);
            }
        }
        Arrays.sort(res);
        int sum = 0;
        for (int i = res.length-1, j = 0; j < maxOnes; i--, j++) {
            sum += res[i]; 
        }
        return sum;
    }
    
    We isolate a square at the top left position with sides = sideLength. Then we put 1 at some position (x,y) in this sqare. Our onesCount increases. If we put 1 at positions (a * sideLength + x, b * sideLength + y) our onesCount remains the same. So we just try to put 1 in the top left sqare and calculate how many more points we can have without affecting maxOnes. Then chose maxOnes number of points with maximum number of 1 it can give us.

class Point implements Comparable<Point>
    {
        int x;
        int y;
        int count;
        public Point(int x, int y, int width, int height, int sideLength)
        {
            this.x = x;
            this.y = y;
            count = (width/sideLength)*(height/sideLength) + //number of whole squares with side sideLength in our matrix
			    (x < width% sideLength ? 1 : 0)*(height/sideLength) + //if we can fit 1 into part squares to the right
                (y < height % sideLength ? 1 : 0)*(width/sideLength) + //if we can fit 1 into part squares at the bottom
				(x < width% sideLength &&  y < height % sideLength ? 1 : 0);//if we can fit 1 into bottom right square
        }
        @Override
        public int compareTo(Point other)
        {
            return  other.count - this.count; //for max priority queue so points sorted in descending order
        }
    }
    public int maximumNumberOfOnes_easyToUnderstand(int width, int height, int sideLength, int maxOnes) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int x = 0; x < sideLength; x++)
            for (int y = 0; y < sideLength; y++)
                pq.offer(new Point(x, y, width, height, sideLength));
        int res = 0;
        for (int i = 0; i < maxOnes; i++)
        {
            Point p = pq.poll();
            res += p.count;
        }
        return res;
    }
}