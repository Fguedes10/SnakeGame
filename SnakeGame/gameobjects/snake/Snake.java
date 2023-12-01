package week4.SnakeGame.gameobjects.snake;
import week4.SnakeGame.field.Field;
import week4.SnakeGame.field.Position;

import java.util.LinkedList;
import java.util.List;

import static week4.SnakeGame.gameobjects.snake.Direction.*;

public class Snake {
    private boolean alive;
    private Direction direction;
    private final LinkedList<Position> snakeBody;

    public Snake(){
        this.alive = true;
        this.direction = LEFT;
        this.snakeBody = new LinkedList<>(List.of(new Position[]{
                new Position(50, 12),
                new Position(51, 12)}));
        }

    public void increaseSize() {
        snakeBody.offer(new Position());
    }

    public void move(){
        if(alive){
            move(direction);
        }
    }

    private void move(Direction direction) {
        if(alive) {
            this.direction = direction;
            for (int i = getSnakeSize() - 1; i > 0; i--) {
                snakeBody.get(i).setCol(snakeBody.get(i - 1).getCol());
                snakeBody.get(i).setRow(snakeBody.get(i - 1).getRow());
            }
            getHead().setRow(getHead().getRow() + direction.moveRow);
            getHead().setCol(getHead().getCol() + direction.moveCol);
        }
    }

    public void checkForbiddenDirections(Direction direction) {
        if(direction.opposite.equals(this.direction.toString())){
            direction = this.direction;
        }
        move(direction);
    }

    public void die() {
        alive = false;
        Field.checkMaxScore();
    }

    public boolean isAlive() {
        return alive;
    }

    public LinkedList<Position> getFullSnake(){
        return snakeBody;
    }

    public Position getHead() {
        return snakeBody.getFirst();
    }

    public Position getTail() {
        return snakeBody.getLast();
    }

    public int getSnakeSize() {
        return snakeBody.size();
    }
}