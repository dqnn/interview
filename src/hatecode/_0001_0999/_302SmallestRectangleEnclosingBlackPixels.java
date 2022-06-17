package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SmallestRectangleEnclosingBlackPixels
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 302. Smallest Rectangle Enclosing Black Pixels
 */
public class _302SmallestRectangleEnclosingBlackPixels {
    /**
     * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
     * The black pixels are connected, i.e., there is only one black region.
     * Pixels are connected horizontally and vertically. Given the location (x, y) of
     * one of the black pixels, return the area of the smallest (axis-aligned) rectangle
     * that encloses all black pixels.

     For example, given the following image:

     [
     "0010",
     "0110",
     "0100"
     ]

     011
     011
     011

     and x = 0, y = 2,
     Return 6.

     time : O(m log n + n log m)
     space : O(1)

     * @param image
     * @param x
     * @param y
     * @return
     */
    // thinking process:
    // problem is table with black and white points, black horizonte or vertical to
    // form a rectangle, we have to find the min area which cover all black points
    
    //one easy way to think it is go through m * n points, and if we have '1' point, 
    // then we update with four coordination, so the model and basic template is four
    // number can let us know its boundary, here the x--> right, y-->down
    // so our width left, right, height is bottom and top
    // left is min of j and right is max of j
    // height top is min of i and bottom is max of i since y --> down
/*    left = min(left, x)
            right = max(right, x)
            top = min(top, y)
            bottom = max(bottom, y)
            Return (right - left + 1) * (bottom - top + 1) */
    // how can we improve this, 
    // so still same as above, we want to get 4 coordinations, 
    // but we find a way faster than above, since one point is given, 
    // horizonte or vertical, so we can try to leverage Binary search, 
    //for left, we want to find from [0, y] whether there is mid != black then
    //move end = mid, 
    //for right, [y， col -1], so mid != black, we move end = mid
    //same for up and down
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length < 1 || image[0].length < 1) {
            return 0;
        }
        int row = image.length;
        int col = image[0].length;
        
        int left = binarySearchLeft(image, 0, y, true);
        //find right part
        int right = binarySearchRight(image, y, col - 1, true);

        int top = binarySearchLeft(image, 0, x, false);
        int bottom = binarySearchRight(image, x, row - 1, false);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int binarySearchLeft(char[][] image, int l, int r, boolean isHor) {
        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            // this means there was black point in this vertical straight line, 
            //isHor means isHorizonte, width
            if (hasBlack(image, mid, isHor)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        // we pre process left because 
        if (hasBlack(image, l, isHor)) {
            return l;
        }
        return r;
    }

    private int binarySearchRight(char[][] image, int l, int r, boolean isHor) {
        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            //means we have black points
            if (hasBlack(image, mid, isHor)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (hasBlack(image, r, isHor)) {
            return r;
        }
        return l;
    }

    private boolean hasBlack(char[][] image, int x, boolean isHor) {
        if (isHor) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][x] == '1') return true;
            }
        } else {
            for (int i = 0; i < image[0].length; i++) {
                if (image[x][i] == '1') return true;
            }
        }
        return false;
    }
    
    //this implementation only has 1 bianrySearch function
    public int minArea_OneBS(char[][] img, int x, int y) {
        int minx = binarySearch(img, 0, x, true, true);
        int maxx = binarySearch(img, x + 1, img.length, true, false);
        int miny = binarySearch(img, 0, y, false, true);
        int maxy = binarySearch(img, y + 1, img[0].length, false, false);
        
        return (maxx - minx) * (maxy - miny);
    }

    private int binarySearch(char[][] img, int l, int r, boolean row, boolean forMin) {
        if (l == r) return l;

        int mid = l + (r - l) / 2;
        if (hasBlack(img, mid, row) == forMin)
            return binarySearch(img, l, mid, row, forMin);
        else
            return binarySearch(img, mid + 1, r, row, forMin);
    }
    
    //brute force O(mn)
    // thinking process, we 
    public int minArea2(char[][] image, int x, int y) {
        if (image == null || image.length < 1 || image[0].length < 1) {
            return 0;
        }
        int left = y, right = y, up =x, down =x;
        //or we don't need this, 
        // int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE, 
        //up =Integer.MIN_VALUE, down =Integer.MAX_VALUE;
        for(int i = 0; i < image.length;i++) {
            for(int j = 0; j < image[0].length; j++) {
                if (image[i][j] == '1') {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    up = Math.max(up, i);
                    down = Math.min(down, i);
                }
            }
        }
        return (right - left + 1) * (up - down + 1);
    }
}
