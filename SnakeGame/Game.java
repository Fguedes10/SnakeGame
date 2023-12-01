package week4.SnakeGame;

import week4.SnakeGame.field.Field;
import week4.SnakeGame.field.Position;
import week4.SnakeGame.gameobjects.fruit.Fruit;
import week4.SnakeGame.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import static week4.SnakeGame.gameobjects.snake.Direction.*;
import java.util.*;

public class Game {
    private Snake snake;
    private Fruit fruit;
    private final int delay;

    public Game(int cols, int rows, int delay) {
        this.delay = delay;
        Field.init(cols, rows);
        snake = new Snake();
        int col = random(Field.getWidth() - 2);
        int row = random(Field.getHeight() -2);
        fruit = new Fruit(new Position(col, row)); //generateFruit();
    }

    public void start() throws InterruptedException {

        generateFruit();

        while (true) {//snake.isAlive()
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            generateFruit();
            checkCollisions();
            checkSnakeHasEaten();
            Field.drawSnake(snake);
        }
    }
    private int random(int max){
        return new Random().nextInt(max - 2) + 2;
    }

    private void generateFruit() {
        Field.drawFruit(this.fruit);
        while (fruit.isEaten()) {
            Set<Position> set = snakePositions();
            int col = random(Field.getWidth() - 2);
            int row = random(Field.getHeight() -2);
            if (col > 0 && row > 0) {
                if (set.contains(new Position(col, row))) {
                    generateFruit();
                }
            }
            this.fruit = new Fruit(new Position(col, row));
        }
    }

    private void moveSnake() throws InterruptedException {

        Key k = Field.readInput();

        if (k != null) {
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

                case Tab:
                    Field.clearSnake(snake);
                    Field.clearFruit(fruit);
                    snake = new Snake();
                    fruit = new Fruit(new Position(30, 3));
                    //score = 0;
                    //delay = 100;
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