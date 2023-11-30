package week4.SnakeGame;

import week4.SnakeGame.field.Field;
import week4.SnakeGame.gameobjects.fruit.Fruit;
import week4.SnakeGame.gameobjects.snake.Direction;
import week4.SnakeGame.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        // generateFruit(); // uncomment when it's time to introduce fruits

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
    }

    private void generateFruit() {
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

    }
}
