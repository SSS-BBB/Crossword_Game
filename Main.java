public class Main {
    public static void main(String[] args) {
        Game game = new Game(5, 5, new String[]{"KAOW", "MATH"});
        game.initGame();
        game.showGame();
    }
}