package tic_tac_toe.models;

public class Board {
    private final Piece[][] grid;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.grid = new Piece[size][size];
    }

    public boolean isValidMove(int row, int col) {
        return (row >= 0) && (row < size) && (col >= 0) && (col < size) && grid[row][col] == null;
    }

    public void makeMove(int row, int col, Piece piece) {
        grid[row][col] = piece;
    }

    public boolean checkWin(int row, int col, Piece piece) {
        return checkRow(row, piece) || checkCol(col, piece) || checkDiagonals(piece);
    }

    private boolean checkRow(int row, Piece piece) {
        for (int i = 0; i < size; i++) {
            if (grid[row][i] != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col, Piece piece) {
        for (int i = 0; i < size; i++) {
            if (grid[i][col] != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals(Piece piece) {
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != piece) {
                diagonal1 = false;
            }
            if (grid[i][size - i - 1] != piece) {
                diagonal2 = false;
            }
        }
        return diagonal1 || diagonal2;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(grid[row][col].getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }
}
