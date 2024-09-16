import java.util.Random;
import java.util.Scanner;

public class Game {

    private final String REMOVED_SIGN = "-";

    private int rowNum, colNum;
    private String[] words;

    private Board board;

    public Game(int rowNum, int colNum, String[] words) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.words = words;

        board = new Board(rowNum, colNum);
    }

    public void playGame() {
        initGame();

        Scanner scan = new Scanner(System.in);

        while (countWord() > 0) {
            showGame();

            int row1, row2, col1, col2;

            row1 = inputIndex("Enter selected row1: ", 0);
            col1 = inputIndex("Enter selected col1: ", 1);
            row2 = inputIndex("Enter selected row2: ", 0);
            col2 = inputIndex("Enter selected col2: ", 1);

            // 1, -1, or 0
            int rowDir = getDir(row2 - row1);
            int colDir = getDir(col2 - col1);


            int dis = rowDir != 0 ? (row2 - row1) : (col2 - col1);
            dis = Math.abs(dis);

            String selectedWord = "";
            for (int i = 0; i <= dis; i++) {
                String c = board.getBoardValue(row1 + (i*rowDir), col1 + (i*colDir));
                if (c == null) {
                    selectedWord = "";
                    break;
                }
                selectedWord += c;
            }

            if (checkWord(selectedWord)) {
                // Correct Word Selected
                System.out.println(selectedWord + " is a word in the list!");

                // Remove the word from the board
                for (int i = 0; i <= dis; i++) {
                    board.setBoardValue(row1 + (i*rowDir), col1 + (i*colDir), board.EMPTY_SIGN);
                }
            }
            else {
                // Wrong Word Selected
                if (selectedWord != "") {
                    System.out.println(selectedWord + " is not in the lis!");
                }
                else {
                    System.out.println("The input is invalid.");
                }
            }
        }

        System.out.println("-----------------------------");
        System.out.println("You got all the Word!, Congrats!");
        System.out.println("-----------------------------");
    }

    private boolean checkWord(String selectedWord) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(selectedWord)) {
                words[i] = REMOVED_SIGN;
                return true;
            }
        }
        return false;
    }

    private int inputIndex(String inputMsg, int dir) {
        Scanner scan = new Scanner(System.in);
        System.out.print(inputMsg);
        int index = scan.nextInt() - 1;

        while (!checkIndex(index, dir)) {
            System.out.print("The input is invalid, Please enter again: ");
            index = scan.nextInt() - 1;
        }

        return index;
    }

    private boolean checkIndex(int index, int dir) {
        // dir: 0 -> row
        //      1 -> col
        if (dir == 0) {
            return !board.isInValidIndex(index, 0);
        }

        return !board.isInValidIndex(0, index);
    }

    private int getDir(int dir) {
        if (dir < 0)
            return -1;
        
        if (dir > 0)
            return 1;
        
        return 0;
    }

    private void initGame() {

        for (String word : words) {
            setWordToBoard(word);
        }

        randomAlphabetToBoard();
    }

    private void showGame() {
        System.out.println("------------------");
        showWords();
        board.showBoard();
    }

    private void showWords() {
        System.out.println("WORDS:");
        System.out.println("------------");
        for (String word : words) {
            // Show only word that has not been removed
            if (!word.equals(REMOVED_SIGN)) {
                System.out.println(word);
            }
        }
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
        int checkCount = 0;
        final int CHECK_LIMIT = 1000;
        while (!check) {
            direction = rand.nextInt(8);
            check = checkDirection(initRow, initCol, word, direction);
            checkCount++;

            // No possible point in board for this word
            if (checkCount > CHECK_LIMIT) {
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(word)) {
                        words[i] = REMOVED_SIGN;
                    }
                }
                return;
            }
        }

        setWordToBoard(initRow, initCol, word, direction);
        
    }

    private int countWord() {
        // Count every word in the list that is not removed
        int count = 0;
        for (String word : words) {
            if (!word.equals(REMOVED_SIGN)) {
                count++;
            }
        }
        return count;
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
            String c = String.valueOf(word.charAt(i));
            board.setBoardValue(currentRow, currentCol, c);
        }
    }
}
