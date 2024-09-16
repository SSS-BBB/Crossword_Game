import java.util.Random;

public class Game {

    private int rowNum, colNum;
    private String[] words;

    private Board board;

    public Game(int rowNum, int colNum, String[] words) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.words = words;

        board = new Board(rowNum, colNum);
    }

    public void initGame() {
        for (String word : words) {
            setWordToBoard(word);
        }

        randomAlphabetToBoard();
    }

    public void showGame() {
        board.showBoard();
    }

    private void randomAlphabetToBoard() {
        Random rand = new Random();
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board.isEmpty(i, j)) {
                    // Random alphabet
                    int randIndex = rand.nextInt(alphabets.length());
                    String c = String.valueOf(alphabets.charAt(randIndex));
                    board.setBoardValue(i, j, c);
                }
            }
        }
    }

    private void setWordToBoard(String word) {

        Random rand = new Random();
        int initRow = rand.nextInt(rowNum);
        int initCol = rand.nextInt(colNum);

        String c = String.valueOf(word.charAt(0));

        // Make sure it's not empty
        while (!board.isEmpty(initRow, initCol) && (board.getBoardValue(initRow, initCol) != c)) {
            initRow = rand.nextInt(rowNum);
            initCol = rand.nextInt(colNum);
        }

        // Random direction of the word 
        int direction = rand.nextInt(8);
        boolean check = checkDirection(initRow, initCol, word, direction);

        // Make sure the direction we chose is valid
        while (!check) {
            direction = rand.nextInt(8);
            check = checkDirection(initRow, initCol, word, direction);
        }

        System.out.println(direction);
        setWordToBoard(initRow, initCol, word, direction);
        
    }

    private boolean checkDirection(int initRow, int initCol, String word, int direction) {
        // Loop through every character in the word
        for (int i = 0; i < word.length(); i++) {
            int currentRow, currentCol;
            switch (direction) {
                case 0:
                    // Upward
                    currentRow = initRow - i;
                    currentCol = initCol;
                    break;
                case 1:
                    // Right-Upward
                    currentRow = initRow - i;
                    currentCol = initCol + i;
                    break;
                case 2:
                    // Right
                    currentRow = initRow;
                    currentCol = initCol + i;
                    break;
                case 3:
                    // Right-Downward
                    currentRow = initRow + i;
                    currentCol = initCol + i;
                    break;
                case 4:
                    // Downward
                    currentRow = initRow + i;
                    currentCol = initCol;
                    break;
                case 5:
                    // Left-Downward
                    currentRow = initRow + i;
                    currentCol = initCol - i;
                    break;
                case 6:
                    // Left
                    currentRow = initRow;
                    currentCol = initCol - i;
                    break;
                case 7:
                    // Left-Upward
                    currentRow = initRow - i;
                    currentCol = initCol - i;
                    break;
            
                default:
                    currentRow = initRow - i;
                    currentCol = initCol;
                    break;
            }
            
            String c = String.valueOf(word.charAt(i));
            if (!board.isEmpty(currentRow, currentCol) && (board.getBoardValue(currentRow, currentCol) != c)) {
                return false;
            }
        }
        return true;
    }

    private void setWordToBoard(int initRow, int initCol, String word, int direction) {
        // Loop through every character in the word
        for (int i = 0; i < word.length(); i++) {
            int currentRow, currentCol;
            switch (direction) {
                case 0:
                    // Upward
                    currentRow = initRow - i;
                    currentCol = initCol;
                    break;
                case 1:
                    // Right-Upward
                    currentRow = initRow - i;
                    currentCol = initCol + i;
                    break;
                case 2:
                    // Right
                    currentRow = initRow;
                    currentCol = initCol + i;
                    break;
                case 3:
                    // Right-Downward
                    currentRow = initRow + i;
                    currentCol = initCol + i;
                    break;
                case 4:
                    // Downward
                    currentRow = initRow + i;
                    currentCol = initCol;
                    break;
                case 5:
                    // Left-Downward
                    currentRow = initRow + i;
                    currentCol = initCol - i;
                    break;
                case 6:
                    // Left
                    currentRow = initRow;
                    currentCol = initCol - i;
                    break;
                case 7:
                    // Left-Upward
                    currentRow = initRow - i;
                    currentCol = initCol - i;
                    break;
            
                default:
                    currentRow = initRow - i;
                    currentCol = initCol;
                    break;
            }
            
            // System.out.println(currentRow);
            // System.out.println(currentCol);
            // System.out.println("-------------");
            String c = String.valueOf(word.charAt(i));
            board.setBoardValue(currentRow, currentCol, c);
        }
    }
}
