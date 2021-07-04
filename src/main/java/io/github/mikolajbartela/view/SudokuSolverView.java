package io.github.mikolajbartela.view;

import io.github.mikolajbartela.solver.SudokuSolver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class SudokuSolverView extends JFrame {

    private final JTextField[][] BOARD;
    private final JFrame SUDOKU_FRAME;
    private final SudokuSolver SOLVER;
    private final JLabel INFO_LABEL;

    public SudokuSolverView(SudokuSolver SOLVER) throws HeadlessException {
        this.SOLVER = SOLVER;
        this.BOARD = new JTextField[9][9];
        this.INFO_LABEL = new JLabel();
        this.SUDOKU_FRAME = new JFrame("Sudoku solver");
        this.createGUI();
    }

    public void showGui() {
        SUDOKU_FRAME.setResizable(false);
        SUDOKU_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SUDOKU_FRAME.setLocationByPlatform(true);
        SUDOKU_FRAME.pack();
        SUDOKU_FRAME.setVisible(true);
    }

    private void createGUI() {
        JPanel mainPanel = this.createMainPanel();
        JPanel sudokuBoardPanel = this.createSudokuBoard();
        JPanel menuPanel = this.createMenuPanel();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridwidth = 3;
        mainPanel.add(sudokuBoardPanel, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(menuPanel);

        SUDOKU_FRAME.add(mainPanel);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(533, 450));
        mainPanel.setLayout(new GridBagLayout());
        return mainPanel;
    }

    private JPanel createSudokuBoard() {
        JPanel sudokuPanel = new JPanel(new GridLayout(3,3));
        sudokuPanel.setPreferredSize(new Dimension(400, 450));
        for (int i = 0; i < 9; i++) {
            JPanel sudokuSmallSquare = createSudokuSquare(i);
            sudokuPanel.add(sudokuSmallSquare);
        }
        return sudokuPanel;
    }

    private JPanel createSudokuSquare(int row) {
        JPanel sudokuSquare = new JPanel(new GridLayout(3,3));
        sudokuSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        for (int j = 0; j < 9; j++) {
            JTextField sudokuCell = createSudokuCell();
            BOARD[3 * (row / 3) + j / 3][j % 3 + row % 3 * 3] = sudokuCell;
            sudokuSquare.add(sudokuCell);
        }
        return sudokuSquare;
    }

    private JTextField createSudokuCell() {
        JTextField sudokuCell = new JTextField();
        sudokuCell.setHorizontalAlignment(JTextField.CENTER);
        sudokuCell.setDocument(new JTextFieldConstraint());
        return sudokuCell;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(133, 450));
        menuPanel.setLayout(new GridLayout(3, 1));

        INFO_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
        displayMessageDefault();
        menuPanel.add(INFO_LABEL);

        JButton solveButton = new JButton("Solve sudoku");
        solveButton.setToolTipText("Solves sudoku");
        solveButton.addActionListener(event -> {
            if (isBoardEmpty()) {
                int[][] sudokuCellNumbers = get2DCellValuesArrayFromBoard();
                boolean solvingSuccess = SOLVER.solve(sudokuCellNumbers);
                if (solvingSuccess) {
                    updateBoard(sudokuCellNumbers);
                    displayMessageSudokuSolved();
                } else {
                    displayMessageInvalidBoard();
                }
            }
        });
        menuPanel.add(solveButton);

        JButton resetButton = new JButton("Reset board");
        resetButton.setToolTipText("Resets sudoku board");
        resetButton.addActionListener(event -> {
            resetGrid();
            displayMessageDefault();
        });
        menuPanel.add(resetButton);

        return menuPanel;
    }

    private boolean isBoardEmpty() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String cellValue = BOARD[row][column].getText();
                if (!cellValue.isEmpty()) return true;
            }
        }
        return false;
    }

    private int[][] get2DCellValuesArrayFromBoard() {
        int[][] cellValuesArray = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String cellValue = BOARD[row][column].getText();
                int number = 0;

                if (!cellValue.isEmpty()) {
                    number = Integer.parseInt(cellValue);
                }
                cellValuesArray[row][column] = number;
            }
        }
        return cellValuesArray;
    }

    private void updateBoard(int[][] cellValues) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                BOARD[row][column].setText(String.valueOf(cellValues[row][column]));
            }
        }
    }

    private void displayMessageDefault() {
        INFO_LABEL.setText("<html><p style=\"width:100px; text-align: center\">"+
                "Input numbers to corresponding cells and click \"Solve sudoku\" to solve sudoku!" +
                "</p></html>");
    }

    private void displayMessageInvalidBoard() {
        INFO_LABEL.setText("<html><p style=\"width:100px; text-align: center; color:red; font-weight: 700\">"+
                "Unsolvable sudoku board! Try different number." +
                "</p></html>");
    }

    private void displayMessageSudokuSolved() {
        INFO_LABEL.setText("<html><p style=\"width:100px; text-align: center; color:green; font-weight: 700\">"+
                "Sudoku solved!" +
                "</p></html>");
    }

    private void resetGrid() {
        Arrays.stream(BOARD)
                .forEach(jTextFields -> {
                    Arrays.stream(jTextFields)
                            .forEach(cell -> cell.setText(""));
                });
    }

}
