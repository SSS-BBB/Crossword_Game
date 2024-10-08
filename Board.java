import java.util.Arrays;

public class Board {

    public final String EMPTY_SIGN = "-";

    private int rowNum = 0, colNum = 0;
    private String[][] board;
    private boolean[][] boardState;

    public Board(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        initBoard();
    }

    private void initBoard() {
        board = new String[rowNum][colNum];
        boardState = new boolean[rowNum][colNum];

        // Fill board with empty sign
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                board[row][col] = EMPTY_SIGN;
            }
        }
    }

    public void showBoard() {

        // Show Column number
        System.out.print("  ");
        for (int col = 0; col < colNum; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int row = 0; row < rowNum; row++) {
            // Show row number
            System.out.print((row + 1) + " ");

            // Show value in the board
            for (int col = 0; col < colNum; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void setBoardValue(int row, int col, String value) {
        if (isInValidIndex(row, col)) {
            return;
        }

        board[row][col] = value;
    }

    public void setBoardState(int row, int col, boolean state) {
        if (isInValidIndex(row, col)) {
            return;
        }

        boardState[row][col] = state;
    }

    public boolean isEmpty(int row, int col) {
        if (isInValidIndex(row, col)) {
            return false;
        }

        return board[row][col] == EMPTY_SIGN;
    }

    public String getBoardValue(int row, int col) {
        if (isInValidIndex(row, col)) {
            return null;
        }

        return board[row][col];
    }

    public boolean isInValidIndex(int row, int col) {
        return row < 0 || col < 0 || row >= rowNum || col >= colNum;
    }

    public String[][] getBoard() {
        return board;
    }
}
