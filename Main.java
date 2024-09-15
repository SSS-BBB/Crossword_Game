public class Main {
    public static void main(String[] args) {
        Game game = new Game(5, 5, new String[]{"KAOW", "MATH"});
        game.initGame();
        game.showGame();
        // String[][] temp = new String[5][5];
        // temp[3][3] = "D";
        // showArray(temp);
    }

    public static void showArray(String[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
    }
}