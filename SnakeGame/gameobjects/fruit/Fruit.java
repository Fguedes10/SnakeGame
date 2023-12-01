package week4.SnakeGame.gameobjects.fruit;

import week4.SnakeGame.field.Position;

public class Fruit {
    private final Position position;
    private boolean eaten;

    public Fruit(Position position){
        this.position = position;
        eaten = false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}