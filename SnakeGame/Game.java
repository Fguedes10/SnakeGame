package week4.SnakeGame;

import week4.SnakeGame.field.Field;
import week4.SnakeGame.field.Position;
import week4.SnakeGame.gameobjects.fruit.Fruit;
import week4.SnakeGame.gameobjects.snake.Direction;
import week4.SnakeGame.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.util.*;


public class Game {

    private Snake snake;
    private Fruit fruit = new Fruit(new Position(30, 3));
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit(); // uncomment when it's time to introduce fruits

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkSnakeHasEaten();
            generateFruit();
            checkCollisions();
            Field.drawSnake(snake);
        }
    }
    private int random(int max, int min){
        return new Random().nextInt(max + 1 - min) + min;
    }

    private void generateFruit() {
        Field.drawFruit(this.fruit);
        while (fruit.isEaten()) {
            Set<Position> set = snakePositions();
            int col = random(Field.getWidth() - 1, 1);
            int row = random(Field.getHeight() -1, 1);
            if (col > 0 && row > 0) {
                if (set.contains(new Position(col, row))) {
                    generateFruit();
                }
            }
            this.fruit = new Fruit(new Position(col, row));
        }
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
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
        //Set<Position> set = new HashSet<>(snake.getFullSnake());
        return snakePositions().size() != snake.getSnakeSize();
    }
    private Set<Position> snakePositions() {
        return new HashSet<>(snake.getFullSnake());
    }

    private void checkSnakeHasEaten(){
        if(fruit.getPosition().equals(snake.getHead())){
            fruit.setEaten(true);
            snake.increaseSize();
        }
    }
}