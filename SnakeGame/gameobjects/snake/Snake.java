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
    private Position lastElementCreated = null;
    private Position tail = new Position(52, 12);
    private final Position[] arr = new Position[]{head, tail};
    private final LinkedList<Position> snakeBody = new LinkedList<>(List.of(arr));

    public Snake(){
        snakeBody.offerFirst(head);
        snakeBody.offer(tail);
        lastElementCreated = new Position(getHead().getCol()+1, getHead().getRow());
        snakeBody.add(snakeBody.indexOf(head), lastElementCreated);
    }

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
        this.direction = direction;
        switch (direction) {
            case UP:
                getFullSnake().remove(getHead());
                head.setRow(head.getRow()-1);
                getFullSnake().addFirst(head);
                return;

            case DOWN:
                getHead().setRow(getHead().getRow()+1);
                getFullSnake().add(0, head);
                return;

            case LEFT:
                getHead().setCol(getHead().getCol()-1);
                getFullSnake().add(0, head);
                return;

            case RIGHT:
                getHead().setCol(getHead().getCol()+1);
                getFullSnake().add(0, head);
                return;
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
        return snakeBody.peek();
    }

    public Position getTail() {
        return snakeBody.peekLast();
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