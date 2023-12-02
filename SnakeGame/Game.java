package week4.SnakeGame;

import week4.SnakeGame.field.Field;
import week4.SnakeGame.field.Position;
import week4.SnakeGame.gameobjects.fruit.Fruit;
import week4.SnakeGame.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import static com.googlecode.lanterna.input.Key.Kind.*;
import static week4.SnakeGame.gameobjects.snake.Direction.*;
import java.util.*;
import week4.SnakeGame.Util.Util;

public class Game {
    private int delay;
    private Snake snake;
    private boolean pause;
    private boolean hard;
    private Fruit fruit;

    public Game(int cols, int rows, int delay) {
        this.delay = delay;
        Field.init(cols, rows);
        snake = new Snake();
        this.pause = true;
        this.hard = false;
    }
    public void start() throws InterruptedException {
        while(true) {
            if (gameModeChosen()) {
                generateFruit();

                while (true) {
                    Thread.sleep(delay);
                    generateFruit();
                    checkCollisions();
                    Field.drawSnake(snake);
                    Field.drawFruit(this.fruit);
                    Field.clearTail(snake);
                    moveSnake();
                    checkSnakeHasEaten();
                }
            }
        }
    }
    private boolean gameModeChosen() {
        Key k = Field.readInput();
        if(k != null) {
            if (k.getKind() == Backspace) {
                hard = false;
                Field.clearMenu();
                setPause(false);
                return true;
            }
            if (k.getKind() == Home) {
                hard = true;
                Field.clearMenu();
                setPause(false);
                return true;
            }
        }
        return false;
    }

    private void moveSnake() {
        Key k = Field.readInput();
        if (k != null) {
            switch (k.getKind()) {
                case Tab:
                    Field.clearSnake(snake);
                    Field.clearFruit(fruit);
                    Field.drawWalls();
                    snake = new Snake();
                    fruit = new Fruit(new Position(Util.randomCol(), Util.randomRow()));
                    Field.scoreCounter = 0;
                    return;

                case Enter:
                    setPause(!pause);
                    return;

                case Escape:
                    System.exit(1);
                    return;
            }
            if(!pause){
                switch (k.getKind()) {
                    case ArrowUp:
                        snake.checkForbiddenDirections(UP);
                        return;

                    case ArrowDown:
                        snake.checkForbiddenDirections(DOWN);
                        return;

                    case ArrowLeft:
                        snake.checkForbiddenDirections(LEFT);
                        return;

                    case ArrowRight:
                        snake.checkForbiddenDirections(RIGHT);
                        return;
                }
            }
        }
        if(!pause){
            snake.move();
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

    private void checkCollisions() {
        int snakeHeadCol = snake.getHead().getCol();
        int snakeHeadRow = snake.getHead().getRow();
        if (snakeHeadCol == 0||snakeHeadRow == 0||snakeHeadCol == Field.getWidth()-1||
                snakeHeadRow == Field.getHeight()-1||checkIfSnakeCollidedWithItself()){
            snake.die();
        }
    }

    private void checkSnakeHasEaten(){
        if(fruit.getPosition().equals(snake.getHead())){
            fruit.setEaten(true);
            Field.scoreCounter++;
            snake.increaseSize();
            if(hard){
                if (delay > 50) {
                    delay -= 1;
                }
            }
        }
    }

    private Set<Position> snakePositions() {
        return new HashSet<>(snake.getFullSnake());
    }

    private boolean checkIfSnakeCollidedWithItself() {
        return snakePositions().size() != snake.getSnakeSize();
    }
    public void setPause(boolean pause) {
        this.pause = pause;
    }
}