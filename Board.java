import java.util.Arrays;

public class Board {

    private int rowNum = 0, colNum = 0;
    private String[][] board;

    public Board(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        initBoard();
    }

    private void initBoard() {
        board = new String[rowNum][colNum];

        // Create array containing string for each row in a board
        String[] tempBoardRow = new String[colNum];
        Arrays.fill(tempBoardRow, "-");

        // Fill in every row
        Arrays.fill(board, tempBoardRow);
    }

    public void showBoard() {
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void setBoardValue(int row, int col, String value) {
        board[row][col] = value;
    }

    public String[][] getBoard() {
        return board;
    }
}
