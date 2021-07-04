package io.github.mikolajbartela;

import io.github.mikolajbartela.solver.BacktrackingSudokuSolver;
import io.github.mikolajbartela.solver.SudokuSolver;
import io.github.mikolajbartela.view.SudokuSolverView;

public class Sudoku {
    public static void main(final String[] args) {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolverView sudokuSolverView = new SudokuSolverView(solver);

        sudokuSolverView.showGui();
    }
}
