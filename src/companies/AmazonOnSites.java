package companies;

import java.util.*;


/*
 * 
 * implemnet linux find command as an api ,the api willl support finding files that has given size requirements and a file with a certain format like

find all file >5mb
find all xml
Assume file class
{
get name()
directorylistfile()
getFile()
create a library flexible that is flexible
Design clases,interfaces.
 */

public class AmazonOnSites {

}



/*
 * design battle game
 * 
 * Problem description

The game is played on two fields, each 10 X 10 squares. The columns are labeled A-J, and the rows are labeled 1-10. Each player's fleet of 
ships consists of one aircraft carrier, one battleship, one destroyer, one submarine and one cruiser. The size and shape of each ship is as follows:

Destroyer (2 squares): 
Submarine (3 squares): 
Cruiser (3 squares): 
Battleship (4 squares): 
Aircraft Carrier (5 squares): 
Before the game starts, each player secretly places their ships anywhere on their own playing field. Ships cannot overlap one another, 
but may be placed either vertically or horizontally.

The first turn is determined by some random means (throwing a die). Players take turns to try to guess the location of the other's ships by naming 
a square (e.g. F7). The opponent declares the square to be a hit or a miss, depending on whether there is a ship occupying that square. When all 
the squares occupied by a particular ship have been guessed, the player must announce that that particular ship is sunk. A player keeps track of 
the hits and misses on a copy of the opponent's field.

The first player to sink all the other's ships is the winner.
 * 
 */

 /*
  *    Game, 
       ship, Destroyer, Submarine...
       
       BattleGame -- board properties, isHit(Player, int[] coordination), 

       Player boolean isHit(int[] coordination), 

       GameApp -- init(), buildBattleGame(BattleGameConfiguration c)

       class Ship {

       }


  */

   enum ShipType {
    Destroyer,
    Submarine,
    Cruiser,
    Battleship, 
    Aircraft_Carrier; 
   }

  abstract class Ship {
        ShipType type;
         
  }


  class Destroyer extends Ship {

  }

  class Submarine extends Ship {

  }

  class Cruiser extends Ship {

  }

    class Battleship extends Ship {

  }


    class Aircraft_Carrier extends Ship {

  }

   class BattleShipFactory {
    Ship buildShip(ShipType type){
        return new Aircraft_Carrier();
    }


   }



  

