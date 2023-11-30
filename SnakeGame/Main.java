package week4.SnakeGame;

public class Main {

    public static void main(String[] args) {
       Game game = new Game(100, 25, 1000);
        try {
            game.start();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}