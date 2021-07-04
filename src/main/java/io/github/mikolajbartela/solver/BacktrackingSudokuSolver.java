package io.github.mikolajbartela.solver;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private final int BOARD_SIZE = 9;

    @Override
    public boolean solve(int[][] board) {
        if (!isBoardValid(board)) return false;
        return solveBoard(board);
    }

    private boolean solveBoard(int[][] board) {
        Position emptyCellPosition = findFirstEmptyCell(board);
        if (emptyCellPosition == null) {
            return true;
        }
        else {
            for (int number = 1; number <= BOARD_SIZE; number++) {
                if (isNumberValidAtPosition(board, number, emptyCellPosition)) {
                    board[emptyCellPosition.getROW()][emptyCellPosition.getCOLUMN()] = number;

                    if (solveBoard(board)) return true;

                    board[emptyCellPosition.getROW()][emptyCellPosition.getCOLUMN()] = 0;
                }
            }
        }

        return false;
    }

    private boolean isBoardValid(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row ++) {
            for(int column = 0; column < BOARD_SIZE; column++) {
                int checkedNumber = board[row][column];
                if (checkedNumber != 0) {
                    Position position = new Position(row, column);
                    if (!isNumberValidAtPosition(board, checkedNumber, position)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Position findFirstEmptyCell(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == 0) {
                    return new Position(row, column);
                }
            }
        }
        return null;
    }

    private boolean isNumberValidAtPosition(int[][] board, int number, Position position) {
        return rowConstraint(board, number, position) &&
                columnConstraint(board, number, position) &&
                boxConstraint(board, number, position);

    }

    private boolean rowConstraint(int[][] board, int number, Position position) {
        for (int column = 0; column < BOARD_SIZE; column++) {
            if (board[position.getROW()][column] == number && position.getCOLUMN() != column ) {
                return false;
            }
        }
        return true;
    }

    private boolean columnConstraint(int[][] board, int number, Position position) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][position.getCOLUMN()] == number && position.getROW() != row) {
                return false;
            }
        }
        return true;
    }

    private boolean boxConstraint(int[][] board, int number, Position position) {
        int BOX_SIZE = 3;
        int boxStartColumn = position.getCOLUMN() - position.getCOLUMN() % BOX_SIZE;
        int boxStartRow = position.getROW() - position.getROW() % BOX_SIZE;
        for (int row = boxStartRow; row < boxStartRow + BOX_SIZE; row++) {
            for (int column = boxStartColumn; column < boxStartColumn + BOX_SIZE; column ++) {
                if (board[row][column] == number && (position.getROW() != row || position.getCOLUMN() != column)) {
                    return false;
                }
            }
        }
        return true;
    }

}
