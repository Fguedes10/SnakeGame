package week4.SnakeGame.field;

import week4.SnakeGame.gameobjects.fruit.Fruit;
import week4.SnakeGame.gameobjects.snake.Snake;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;

public final class Field {

    private static final String BORDER_STRING = "▒";
    private static final String SNAKE_BODY_STRING = "#";
    private static final String SNAKE_HEAD_STRING = "0";
    private static final String FRUIT_STRING = "@";
    private static final String WELCOME = "WELCOME";
    public static int maxScore;
    public static int scoreCounter;
    private static int width;
    private static int height;
    private static Screen screen;
    private static ScreenWriter screenWriter;

    public static void checkMaxScore(){
        if(scoreCounter > maxScore){
            maxScore = scoreCounter;
        }
    }
    public static void init(int width, int height) {

        screen = TerminalFacade.createScreen();

        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);

        screenWriter = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        screen.startScreen();

        drawWalls();
        drawMainMenu();
        screen.refresh();
    }

    public static void drawSnake(Snake snake) {

        Terminal.Color snakeColor = Terminal.Color.GREEN;

        if (!snake.isAlive()){
            snakeColor = Terminal.Color.RED;
        }

        createScoreboard();

        Position head = snake.getHead();

        for (Position p : snake.getFullSnake()) {
            if (!p.equals(head)) {
                screen.putString(p.getCol(), p.getRow(), SNAKE_BODY_STRING, snakeColor, null);
            } else {
                screen.putString(p.getCol(), p.getRow(), SNAKE_HEAD_STRING, snakeColor, null);
            }
        }
        screen.refresh();
    }

    private static void drawMainMenu(){
        for (int i = 25; i < 76; i++) {
            screenWriter.drawString(i, 5, BORDER_STRING);
            screenWriter.drawString(i, 20, BORDER_STRING);
        }
        for (int i = 5; i < 21; i++) {
            screenWriter.drawString(25, i, BORDER_STRING);
            screenWriter.drawString(75, i, BORDER_STRING);
        }
        screenWriter.drawString(47, 7, WELCOME);
        screenWriter.drawString(42, 12, "CHOOSE GAME MODE:");
        screenWriter.drawString(30, 15, "1. NORMAL");
        screenWriter.drawString(45, 15, "2. HARD");
        screenWriter.drawString(58, 15, "3. EXTREME");
    }

    public static void clearMenu(){
        for (int i = 1; i < width-1; i++) {
            for (int j = 1; j < height-1 ; j++) {
                screenWriter.drawString(i, j, " ");
                screenWriter.drawString(i, j, " ");
            }
        }
    }

    private static void createScoreboard() {
        screen.putString(6, 0, "SCOREBOARD: " + scoreCounter, Terminal.Color.GREEN ,null);
        screen.putString(25, 0, "MAX SCORE: " + maxScore, Terminal.Color.YELLOW,null);
        screen.putString(70, 25, "RESTART GAME -> TAB" , Terminal.Color.BLUE,null);
    }

    public static void drawWalls() {
        for (int i = 0; i < width; i++) {
            screenWriter.drawString(i, 0, BORDER_STRING);
            screenWriter.drawString(i, height - 1, BORDER_STRING);
        }

        for (int j = 0; j < height; j++) {
            screenWriter.drawString(0, j, BORDER_STRING);
            screenWriter.drawString(width - 1, j, BORDER_STRING);
        }
    }

    public static Key readInput() {
        return screen.readInput();
    }

    public static void drawFruit(Fruit fruit) {
        screen.putString(fruit.getPosition().getCol(), fruit.getPosition().getRow(), FRUIT_STRING, Terminal.Color.MAGENTA, null);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void clearTail(Snake snake) {
        Position tail = snake.getTail();
        screen.putString(tail.getCol(), tail.getRow(), " ", null, null);
    }

    public static void clearSnake(Snake snake) {
        LinkedList<Position> fullSnake = snake.getFullSnake();
        for(Position p: fullSnake){
            screen.putString(p.getCol(), p.getRow(), " ", null, null);
        }
    }

    public static void clearFruit(Fruit fruit) {
        Position fruitPosition = fruit.getPosition();
        screen.putString(fruitPosition.getCol(), fruitPosition.getRow(), " ", null, null);
    }
}
