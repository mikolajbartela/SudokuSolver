/*
 * Copyright 2021 Mikołaj Bartela
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.mikolajbartela.solver;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BacktrackingSudokuSolverTest {

    @Test
    void solve_True_PossibleSudokuBoard() {
        // given
        SudokuSolver solver = new BacktrackingSudokuSolver();
        int[][] board = {
                {1,5,2,7,4,3,0,0,9},
                {0,0,0,0,0,2,0,4,7},
                {0,7,0,8,0,6,5,0,0},
                {6,3,4,0,2,0,0,0,5},
                {7,0,0,0,0,5,0,3,0},
                {0,0,5,0,0,9,4,2,0},
                {3,6,0,0,0,0,9,0,1},
                {0,0,0,6,0,1,0,0,0},
                {2,0,0,0,0,0,6,8,0}
        };
        int[][] correctBoard = {
                {1,5,2,7,4,3,8,6,9},
                {9,8,6,5,1,2,3,4,7},
                {4,7,3,8,9,6,5,1,2},
                {6,3,4,1,2,8,7,9,5},
                {7,2,9,4,6,5,1,3,8},
                {8,1,5,3,7,9,4,2,6},
                {3,6,7,2,8,4,9,5,1},
                {5,9,8,6,3,1,2,7,4},
                {2,4,1,9,5,7,6,8,3}
        };
        // when
        boolean result = solver.solve(board);

        // then
        assertThat(result).isTrue();
        assertThat(board).isEqualTo(board);
    }

    @Test
    void solve_False_ImpossibleSudokuBoard() {
        // given
        SudokuSolver solver = new BacktrackingSudokuSolver();
        int[][] board = {
                {1,5,2,7,4,3,0,0,9},
                {0,2,0,0,0,2,0,4,7},
                {0,7,0,8,0,6,5,0,0},
                {6,3,4,0,2,0,0,0,5},
                {7,0,0,0,0,5,0,3,0},
                {0,0,5,0,0,9,4,2,0},
                {3,6,0,0,0,0,9,0,1},
                {0,0,0,6,0,1,0,0,0},
                {2,0,0,0,0,0,6,8,0}
        };
        // when
        boolean result = solver.solve(board);

        // then
        assertThat(result).isFalse();
    }
}