package hatecode._2000_2999;

/**
 * Definition for a street.
 * interface Street {
 *     public Street(int[] doors);
 *     public void openDoor();
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 *     public void moveLeft();
 * }
 */


interface Street {
      public Street(int[] doors);
      public void openDoor();
      public void closeDoor();
      public boolean isDoorOpen();
      public void moveRight();
      public void moveLeft();
}

public class _2728CountHousesInACircularStreet {
/*
2728. Count Houses in a Circular Street
You are given an object street of class Street that represents a circular street and a positive integer k which represents a maximum bound for the number of houses in that street (in other words, the number of houses is less than or equal to k). Houses' doors could be open or closed initially.

Initially, you are standing in front of a door to a house on this street. Your task is to count the number of houses in the street.

The class Street contains the following functions which may help you:

void openDoor(): Open the door of the house you are in front of.
void closeDoor(): Close the door of the house you are in front of.
boolean isDoorOpen(): Returns true if the door of the current house is open and false otherwise.
void moveRight(): Move to the right house.
void moveLeft(): Move to the left house.
Return ans which represents the number of houses on this street.

 

Example 1:

Input: street = [0,0,0,0], k = 10
Output: 4
*/

/*
K is a guessing number more than house existed in the circuit street, so you need to open/close all door and then count,
note if you have case like street = [1,0,1,1,0], k = 5, 

you need to move one step first then count, if not you will return at last line
*/
    public int houseCount(Street street, int k) {
        for(int i = 0; i< k; i++) {
            street.closeDoor();
            street.moveRight();
        }
        
        int res = 1;
        street.openDoor();
        street.moveRight();
    
        for(int i = 0; i < k; i++) {
            if (street.isDoorOpen()) {
                return res;
            }
            res++;
            street.moveRight();
        }
        
        return 0;
    }
}