package week4.SnakeGame.Util;

import week4.SnakeGame.field.Field;

import java.util.Random;

public class Util {
    public static int randomCol(){return new Random().nextInt((Field.getWidth() - 2) - 2) + 2;}
    public static int randomRow(){return new Random().nextInt((Field.getHeight() -2) - 2) + 2;}
}