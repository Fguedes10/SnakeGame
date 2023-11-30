package week4.SnakeGame.gameobjects.snake;


import week4.SnakeGame.field.Position;

import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static week4.SnakeGame.gameobjects.snake.Direction.*;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive = true;
    private Position head = new Position(50, 12);
    private Position lastElementCreated = new Position(head.getCol()+1, head.getRow());
    private Position tail = new Position(52, 12);
    private final Position[] arr = new Position[]{head, lastElementCreated, tail};
    private LinkedList<Position> snakeBody = new LinkedList<>(List.of(arr));


    public void increaseSize() {

    }

    public void move(Direction direction) {
        if(this.direction == UP && direction == DOWN){
            direction = this.direction;
        }
        if(this.direction == DOWN && direction == UP){
            direction = this.direction;
        }
        if(this.direction == RIGHT && direction == LEFT){
            direction = this.direction;
        }
        if(this.direction == LEFT && direction == RIGHT){
            direction = this.direction;
        }
        for(Position p: snakeBody){
            System.out.println(p.getRow() + " " + p.getCol());
        }
        this.direction = direction;
        for (int i = snakeBody.size()-1; i >= 0 ; i--) {
            if(snakeBody.get(i).equals(snakeBody.getFirst())){
                head.setRow(head.getRow()+direction.rowAdd);
                head.setCol(head.getCol()+direction.colAdd);
                continue;
            }
            snakeBody.get(i).setCol(snakeBody.get(i-1).getCol());
            snakeBody.get(i).setRow(snakeBody.get(i-1).getRow());
        }
    }
    public void move(){
        if(direction == null ){
            move(LEFT);
        }
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public LinkedList<Position> getFullSnake(){
        return snakeBody;
    }

    public Position getHead() {
        return head;
    }

    public Position getTail() {
        return snakeBody.getLast();
    }

    public int getSnakeSize() {
        return snakeBody.size();
    }

    public void setHead(Position head) {
        this.head = head;
    }
}