/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

 package _2000_2999;

 import java.util.*;
 public class _1274NumberOfShipsInARectangle {
    /*
    1274. Number of Ships in a Rectangle
    (This problem is an interactive problem.)
    
    Each ship is located at an integer point on the sea represented by a cartesian plane, and each integer point may contain at most 1 ship.
    
    You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true If there is at least one ship in the rectangle represented by the two points, including on the boundary.
    
    Given two points: the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle. It is guaranteed that there are at most 10 ships in that rectangle.
    
    Submissions making more than 400 calls to hasShips will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
    
     
    
    Example :
    
    
    Input: 
    ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
    Output: 3
    Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
    */

    /*
     * thinking process: O(n)
     * T(n) = 4 T(n/2), 
     * 
     * 
     */
        public int countShips(Sea sea, int[] A, int[] B) {
            
            Map<String, Integer> map = new HashMap<>();
            return helper(sea, B, A, map);
        }
        
        
        private int helper(Sea s, int[] A, int[] B, Map<String, Integer> map) {
            
            //System.out.println("A=" + Arrays.toString(A) + "  B=" + Arrays.toString(B));
            //System.out.println("map=" + map);
            String key = getKey(A, B);
            if (map.containsKey(key)) 
            {
                
                return 0;
            }
            int res = 0;
            
            
            if (!s.hasShips(B, A)) {
                res = 0;
            } else if (A[0] == B[0] && A[1] == B[1]) {
                res = 1;
            } else {
                
                int mx = (A[0] + B[0]) / 2;
                int my = (A[1] + B[1]) / 2;
                //bootom left
                res += helper(s, A, new int[]{mx, my}, map);
                //bottom right
                res += helper(s, new int[]{mx+1, A[1]}, new int[]{B[0], my}, map);
                // top left 
                res += helper(s, new int[]{A[0], my + 1}, new int[]{mx+1, B[1]}, map);
                //top right
                res += helper(s, new int[]{mx + 1, my + 1}, B, map);
            }
            
            map.put(key,res);
            return res;
            
            
        }
        
        private String getKey(int[] A, int[] B) {
            return A[0]+","+A[1]+"-"+B[0]+"," + B[1];
        }
    }