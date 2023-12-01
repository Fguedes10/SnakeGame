package week4.SnakeGame;

import week4.SnakeGame.field.Field;
import week4.SnakeGame.field.Position;
import week4.SnakeGame.gameobjects.fruit.Fruit;
import week4.SnakeGame.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import static week4.SnakeGame.gameobjects.snake.Direction.*;
import java.util.*;
import week4.SnakeGame.Util.Util;

public class Game {
    private int delay;
    private Snake snake;
    private Fruit fruit;
    private boolean pause;

    public Game(int cols, int rows, int delay) {
        this.delay = delay;
        Field.init(cols, rows);
        snake = new Snake();
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void start() throws InterruptedException {

        generateFruit();

        while (true) {//snake.isAlive()
            //System.out.println(pause);
            if(!isPause()) {
                inputs();
                Thread.sleep(delay);
                Field.clearTail(snake);
                generateFruit();
                checkCollisions();
                checkSnakeHasEaten();
                moveSnake();
                Field.drawSnake(snake);
                Field.drawFruit(this.fruit);
            }
        }
    }

    private void generateFruit() {
        if(fruit == null){
            this.fruit = new Fruit(new Position(Util.randomCol(), Util.randomRow()));
        }
        if(fruit.isEaten()) {
            Set<Position> set = snakePositions();
            this.fruit = new Fruit(new Position(Util.randomCol(), Util.randomRow()));
            if (set.contains(fruit.getPosition())) {
                generateFruit();
            }
        }
    }

    private void inputs(){

        Key t = Field.readInput();

        if (t != null) {
            switch (t.getKind()) {
                case Tab:
                    Field.clearSnake(snake);
                    Field.clearFruit(fruit);
                    Field.drawWalls();
                    snake = new Snake();
                    fruit = new Fruit(new Position(Util.randomCol(), Util.randomRow()));
                    Field.scoreCounter = 0;
                    return;

                case Enter:
                    setPause(true);
                    return;

                case Escape:
                    //System.exit(1);
                    setPause(false);
            }
        }
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    setPause(false);
                    snake.checkForbiddenDirections(UP);
                    return;

                case ArrowDown:
                    setPause(false);
                    snake.checkForbiddenDirections(DOWN);
                    return;

                case ArrowLeft:
                    setPause(false);
                    snake.checkForbiddenDirections(LEFT);
                    return;

                case ArrowRight:
                    setPause(false);
                    snake.checkForbiddenDirections(RIGHT);
                    return;

            }
        }
        snake.move();
    }

    private void checkCollisions() {
        if(snake.getHead().getCol() == Field.getWidth()-1|| snake.getHead().getCol() == 0 || snake.getHead().getRow() == Field.getHeight()-1  || snake.getHead().getRow() == 0 || checkIfSnakeCollidedWithItself()){
            snake.die();
        }
    }

    private boolean checkIfSnakeCollidedWithItself() {
        return snakePositions().size() != snake.getSnakeSize();
    }

    private Set<Position> snakePositions() {
        return new HashSet<>(snake.getFullSnake());
    }

    private void checkSnakeHasEaten(){
        if(fruit.getPosition().equals(snake.getHead())){
            fruit.setEaten(true);
            Field.scoreCounter++;
            snake.increaseSize();
        }
    }
}