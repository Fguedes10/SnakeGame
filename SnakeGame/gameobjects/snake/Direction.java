package week4.SnakeGame.gameobjects.snake;


public enum Direction {
    UP(-1, 0, "DOWN"),
    DOWN(+1, 0, "UP"),
    LEFT(0, -1, "RIGHT"),
    RIGHT(0, +1, "LEFT");

    final int moveRow;
    final int moveCol;
    final String opposite;

    Direction(int moveRow, int moveCol, String opposite){
        this.moveRow = moveRow;
        this.moveCol = moveCol;
        this.opposite = opposite;
    }
}