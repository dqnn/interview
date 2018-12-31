package hatecode;

import java.util.*;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Project Name : Leetcode Package Name : leetcode File Name : DesignSnakeGame
 * Creator : duqiang Date : July, 2018 Description :
 * 
 * 353. Design Snake Game
 * 
 * Design a Snake game that is played on a device with screen size = width x
 * height. Play the game online if you are not familiar with the game.
 * 
 * The snake is initially positioned at the top left corner (0,0) with length =
 * 1 unit.
 * 
 * You are given a list of food's positions in row-column order. When a snake
 * eats the food, its length and the game's score both increase by 1.
 * 
 * Each food appears one by one on the screen. For example, the second food will
 * not appear until the first food was eaten by the snake.
 * 
 * When a food does appear on the screen, it is guaranteed that it will not
 * appear on a block occupied by the snake.
 * 
 * Example:
 * 
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * 
 * Snake snake = new Snake(width, height, food);
 * 
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * 
 * |S| | | | | |F|
 * 
 * snake.move("R"); -> Returns 0
 * 
 * | |S| | | | |F|
 * 
 * snake.move("D"); -> Returns 0
 * 
 * | | | | | |S|F|
 * 
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after
 * that, the second food appears at (0,1) )
 * 
 * | |F| | | |S|S|
 * 
 * snake.move("U"); -> Returns 1
 * 
 * | |F|S| | | |S|
 * 
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * 
 * | |S|S| | | |S|
 * 
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class DesignSnakeGame {

    //2D position info is encoded to 1D and stored as two copies 
    Set<Integer> bodyCopy; // this copy is good for fast loop-up for eating body case
    Deque<Integer> body; // this copy is good for updating tail
    int score;
    int[][] food;
    int foodIndex;
    int width;
    int height;
    
    public DesignSnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        bodyCopy = new HashSet<>();
        bodyCopy.add(0); //intially at [0][0]
        body = new LinkedList<>();
        body.offerLast(0);
    }
    public int move(String direction) {
        //case 0: game already over: do nothing
        if (score == -1) {
            return -1;
        }
        // compute new head
        int rowHead = body.peekFirst() / width;
        int colHead = body.peekFirst() % width;
        switch (direction) {
            case "U" : rowHead--;
                       break;
            case "D" : rowHead++;
                       break;
            case "L" : colHead--;
                       break;
            default :  colHead++;
        }
        int head = rowHead * width + colHead;
        
        //case 1: out of boundary or eating body
     // new head is legal to be in old tail's position, remove from set temporarily
        bodyCopy.remove(body.peekLast());  
        if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width 
                || bodyCopy.contains(head)) {
            return score = -1;
        }
        
        // add head for case2 and case3
        bodyCopy.add(head); 
        body.offerFirst(head);
        
        //case2: eating food, keep tail, add head
        if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
            bodyCopy.add(body.peekLast()); // old tail does not change, so add it back to set
            foodIndex++;
            return ++score;
        }
        
        //case3: normal move, remove tail, add head
        body.pollLast();
        return score;
        
    }
    
    //OO solution, which is interview friendly
    class Position{
        int x;
        int y;
        public Position(int x,int y){
            this.x = x;
            this.y = y;
        }
        public boolean equals(Object p){
            return this.x== ((Position)p).x && this.y == ((Position)p).y ;
        }
         @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    
    
    int len;
    int rows ,cols;
    
    int[][] f;
    LinkedList<Position> snake;
    Set<Position> snakeBody;
   
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public void SnakeGame2(int width, int height, int[][] food) {
        this.rows = height;
        this.cols = width;
        this.f = food;
   
        snake = new LinkedList<Position>();
        snake.add(new Position(0,0));
        len = 0;
        snakeBody = new HashSet<>();
        snakeBody.add(snake.peek());
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move2(String direction) {
        //if(len>=food.length) return len;
    
        Position cur = new Position(snake.peek().x,snake.peek().y);
        
        switch(direction){
        case "U": 
            cur.x--;  break;
        case "L": 
            cur.y--; break;
        case "R": 
            cur.y++;   break;
        case "D": 
            cur.x++;   break;
        }
        snakeBody.remove(snake.peekLast());
        if(cur.x<0 || cur.x>= rows || cur.y<0 
           || cur.y>=cols || snakeBody.contains(cur)) return -1;

        snake.addFirst(cur);  
        snakeBody.add(cur);
       
        if(len<f.length){
            Position p = new Position(f[len][0],f[len][1]);           
            if(cur.equals(p)){             
                snakeBody.add(snake.peekLast());
                len++;
            }
        }
        
        //since every call to move, we will always add cur into snake, 
        //so the len will be more than its correct size, so we remove extra ones
        //correct size is len + 1;
        while(snake.size()>len+1) snake.removeLast();
        return len;
    }
}
