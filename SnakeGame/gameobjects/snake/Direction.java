package week4.SnakeGame.gameobjects.snake;


public enum Direction {
    UP(-1, 0),
    DOWN(+1, 0),
    LEFT(0, -1),
    RIGHT(0, +1);

    final int rowAdd;
    final int colAdd;

    Direction(int rowAdd, int colAdd){
        this.rowAdd = rowAdd;
        this.colAdd = colAdd;
    }
}