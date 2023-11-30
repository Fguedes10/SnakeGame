package week4.SnakeGame.gameobjects.snake;


import week4.SnakeGame.field.Position;

import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static week4.SnakeGame.gameobjects.snake.Direction.*;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive = true;
    private Position head = new Position(50, 12);
    private Position tail = new Position(52, 12);
    private final Position[] arr = new Position[]{head, new Position(51, 12), tail};
    private LinkedList<Position> snakeBody = new LinkedList<>(List.of(arr));


    public void increaseSize() {

    }

    public void move(Direction direction) {
        this.direction = direction;
        getFullSnake().getFirst().setRow(11);


        /*switch (direction) {
            case UP:
                Position newHead = new Position(1, 1);
                getFullSnake().addFirst(newHead);
                return;

            case DOWN:
                getFullSnake().getFirst().setRow(getFullSnake().getFirst().getRow()+1);
                return;

            case LEFT:
                getFullSnake().getFirst().setCol(getFullSnake().getFirst().getCol()-1);
                return;

            case RIGHT:
                getFullSnake().getFirst().setCol(getFullSnake().getFirst().getCol()+1);
                return;
        }

         */

    }

    public void move(){
        move(UP);
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
        return tail;
    }

    public int getSnakeSize() {
        return snakeBody.size();
    }

    public void setHead(Position head) {
        this.head = head;
    }

    public void setTail(Position tail) {
        this.tail = tail;
    }
}